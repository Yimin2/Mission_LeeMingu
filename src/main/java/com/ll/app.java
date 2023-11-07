package com.ll;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class app {
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
            if (cmd.equals("종료")) {
                break;
            } else if (cmd.equals("등록")) {
                regit();
            } else if (cmd.equals("목록")) {
                list();
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
}
