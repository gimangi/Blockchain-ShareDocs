import docs.Docs;

import java.util.Scanner;

public class Main {

    private static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {

        String author;
        int difficulty;

        System.out.println("저자의 이름을 입력하세요.");
        author = sc.nextLine();

        System.out.println("블록체인 난이도를 입력하세요.");
        difficulty = enterNumber();

        Docs docs = new Docs(difficulty, author);

        while (true) {
            int command;
            System.out.println("원하는 명령을 선택해주세요.");
            System.out.println("1: 문서 편집\t2: 문서 열람 (자동으로 채굴이 진행됩니다)\t3: 블록체인 검증 결과\t4: 종료");
            command = enterNumber();

            switch (command) {
                case 1:
                    editDocs(docs);
                    break;
                case 2:
                    String result = docs.view();
                    System.out.println("--------------[열람 결과]--------------");
                    System.out.println(result);
                    break;
                case 3:
                    System.out.println("검증 결과 : " + (docs.getBlockChain().isChainValid() ? "이상 없음" : "유효하지 않음"));
                    break;
                default:
                    return;
            }
        }
    }

    private static void editDocs(Docs docs) {
        while (true) {
            int command;
            System.out.println("원하는 명령을 선택해주세요.");
            System.out.println("1: 마지막 줄 뒤에 내용 추가\t2: 지정한 줄에 내용 추가\t3: 지정한 줄에 내용 수정\t4: 지정한 줄 삭제\t5: 편집 종료");
            command = enterNumber();

            switch (command) {
                case 1:
                    docs.appendLast(enterInput());
                    break;
                case 2:
                    docs.append(enterLine(), enterInput());
                    break;
                case 3:
                    docs.update(enterLine(), enterInput());
                    break;
                case 4:
                    docs.delete(enterLine());
                    break;
                default:
                    return;
            }
        }
    }

    private static int enterNumber() {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("올바르지 않은 숫자입니다.");
            }
        }
    }

    private static int enterLine() {
        System.out.println("줄의 번호를 입력해주세요.");
        return enterNumber();
    }

    private static String enterInput() {
        System.out.println("내용을 입력해주세요");
        String input = sc.nextLine();
        return input;
    }
}
