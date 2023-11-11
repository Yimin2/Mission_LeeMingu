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
            if (cmd.equals("종료")) {
                break;
            } else if (cmd.equals("등록")) {
                regit();
            } else if (cmd.equals("목록")) {
                list();
            } else if (cmd.startsWith("삭제")) {
                del(cmd);
            } else if (cmd.startsWith("수정")) {
                modify(cmd);
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

    void del(String cmd) {
        String[] cmdBits = cmd.split("\\?", 2);
        String idParam = cmdBits[1];
        String[] idParamBits = idParam.split("&");
        int id = 0;
        for (int i = 0; i < idParamBits.length; i++) {
            String bitsParam = idParamBits[i];
            String[] bitsParamBits = bitsParam.split("=", 2);
            String nameBitsParamBits = bitsParamBits[0];
            String valueBitsParamBits = bitsParamBits[1];

            if (nameBitsParamBits.equals("id")) {
                id = Integer.parseInt(valueBitsParamBits);
            }
        }
        try {
            sayings.remove(id - 1);
            System.out.printf("%d번 명언이 삭제되었습니다.\n", id);
        } catch (IndexOutOfBoundsException e) {
            System.out.printf("%d번 명언은 존재하지 않습니다.\n", id);
        }
    }

    void modify(String cmd) {
        String[] cmdBits = cmd.split("\\?", 2);
        String idParam = cmdBits[1];
        String[] idParamBits = idParam.split("&");
        int id = 0;

        for (int i = 0; i < idParamBits.length; i++) {
            String bitsParam = idParamBits[i];
            String[] bitsParamBits = bitsParam.split("=", 2);
            String nameBitsParamBits = bitsParamBits[0];
            String valueBitsParamBits = bitsParamBits[1];

            if (nameBitsParamBits.equals("id")) {
                id = Integer.parseInt(valueBitsParamBits);
            }
        }

        try {
            if (id <= sayings.size() && id > 0) {
                Saying saying = sayings.get(id - 1);

                System.out.println("현재 명언 내용: " + saying.content);
                System.out.println("현재 작가: " + saying.name);

                System.out.print("새로운 명언 입력: ");
                content = scanner.nextLine();
                System.out.print("새로운 작가 입력: ");
                name = scanner.nextLine();

                saying.content = content;
                saying.name = name;

                System.out.println(id + "번 명언이 수정되었습니다.");
            } else {
                System.out.printf("%d번 명언은 존재하지 않습니다.\n", id);
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.printf("%d번 명언은 존재하지 않습니다.\n", id);
        }
    }
}
