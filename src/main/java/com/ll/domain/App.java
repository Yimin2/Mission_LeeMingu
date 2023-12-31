package com.ll.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    private String content;
    private String name;
    private Scanner scanner;
    private int count;
    private List<Saying> sayings;

    public App() {
        scanner = new Scanner(System.in);
        sayings = new ArrayList<>();
        count = 1;
    }

    public void run() {
        System.out.println("== 명언 앱 ==");
        while (true) {
            System.out.print("명령 : ");
            String cmd = scanner.nextLine();
            Rq rq = new Rq(cmd);
            switch (rq.getAction()) {
                case "종료":
                    return;
                case "등록":
                    regit();
                    break;
                case "목록":
                    list();
                    break;
                case "삭제":
                    actionRemove(rq);
                    break;
                case "수정":
                    actionModify(rq);
                    break;
            }
        }
    }

    private void regit() {
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

    private void list() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("------------------");
        System.out.println();
        for (int i = sayings.size() - 1; i >= 0; i--) {
            Saying saying = sayings.get(i);
            System.out.printf("%d / %s / %s\n", saying.id, saying.content, saying.name);
        }
    }

    private void actionRemove(Rq rq) {
        int id = rq.getParamAsInt("id", 0);
        if (id == 0) {
            System.out.println("id를 정확히 입력해주세요.");
            return; // 함수를 끝낸다.
        }
        int index = getIndexOfSayingById(id);

        if (index == -1) {
            System.out.printf("%d번 명언은 존재하지 않습니다.\n", id);
            return;
        }

        sayings.remove(index);

        System.out.printf("%d번 명언을 삭제되었습니다.\n", id);
    }

    private int getIndexOfSayingById(int id) {
        for (int i = 0; i < sayings.size(); i++) {
            Saying saying = sayings.get(i);

            if (saying.id == id) {
                return i;
            }
        }

        return -1;
    }

    private void actionModify(Rq rq) {
        int id = rq.getParamAsInt("id", 0);
        if (id == 0) {
            System.out.println("id를 정확히 입력해주세요.");
            return; // 함수를 끝낸다.
        }
        System.out.printf("%d번 명언을 수정합니다.\n", id);
    }
}