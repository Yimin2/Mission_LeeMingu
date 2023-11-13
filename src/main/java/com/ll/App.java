package com.ll;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    String content;
    String name;
    Scanner scanner;
    int count;
    List<Saying> sayings;

    app() {
        scanner = new Scanner(System.in);
        sayings = new ArrayList<>();
        count = 1;
    }

    void run() {
        System.out.println("== 명언 앱 ==");
        while (true) {
            System.out.print("명령 : ");
            String cmd = scanner.nextLine();
            Rq rq = new Rq(cmd);

            System.out.println("rq.getAction : " + Rq.getAction());
            System.out.println("rq.getParamAsInt : " + Rq.getParamAsInt("id", 0));
            if (cmd.equals("종료")) {
                break;
            } else if (cmd.equals("등록")) {
                regit();
            } else if (cmd.equals("목록")) {
                list();
            } else if (cmd.startsWith("삭제")) {
                actionRemove(cmd);
            } else if (cmd.startsWith("수정")) {
                actionModify(cmd);
            }
        }
    }

    void regit() {
        System.out.print("명언 : ");
        content = scanner.nextLine();
        System.out.print("작가 : ");
        name = scanner.nextLine();
        System.out.printf("%d번 명언이 생성되었습니다.", count);
        Saying saying = new Saying(content, name, count);
        System.out.printf(content + "/" + name + "\n");
        count++;
        sayings.add(saying);
    }

    void list() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("------------------");
        System.out.println();
        for (int i = sayings.size() - 1; i >= 0; i--) {
            Saying saying = sayings.get(i);
            System.out.printf("%d / %s / %s\n", saying.count, saying.content, saying.name);
        }
    }

    void actionRemove(String cmd) {
        int id = getParamAsInt(cmd, "id", 0);
        if (id == 0) {
            System.out.println("id를 정확히 입력해주세요.");
            return; // 함수를 끝낸다.
        }
        System.out.printf("%d번 명언을 삭제합니다.\n", id);
    }

    void actionModify(String cmd) {
        int id = getParamAsInt(cmd, "id", 0);
        if (id == 0) {
            System.out.println("id를 정확히 입력해주세요.");
            return; // 함수를 끝낸다.
        }
        System.out.printf("%d번 명언을 수정합니다.\n", id);
    }
}