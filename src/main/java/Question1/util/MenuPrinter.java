package Question1.util;

public class MenuPrinter {
    public static void printMenu(){
        System.out.println("\n===== 게시판 카테고리 조회 메뉴 =====");
        System.out.println("1. 카테고리 ID로 조회");
        System.out.println("2. 카테고리 이름으로 조회");
        System.out.println("3. 카테고리 추가");
        System.out.println("4. 종료");
    }

    public static void printPrompt() {
        System.out.print("선택> ");
    }
}
