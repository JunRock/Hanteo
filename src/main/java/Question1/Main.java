package Question1;

import static Question1.util.MenuPrinter.printMenu;
import static Question1.util.MenuPrinter.printPrompt;

import Question1.global.AppConfig;
import Question1.global.AppConfig.Context;
import Question1.util.Init;

public class Main {

    public static void main(String[] args) {
        Context ctx = AppConfig.createContext();
        new Init(ctx).initEnvironment();
        while (true) {
            printMenu();
            printPrompt();
            String input = ctx.sc.nextLine().trim();
            switch (input) {
                case "1" -> ctx.handler.handleIdLookup();
                case "2" -> ctx.handler.handleNameLookup();
                case "3" -> ctx.handler.handleCreateCategory();
                case "4" -> ctx.handler.handleCreateBoard();
                case "5" -> {
                    System.out.println("프로그램을 종료합니다.");
                    ctx.sc.close();
                    return;
                }
                default -> System.err.println("없는 메뉴입니다. 다시 선택해주세요.");
            }
        }
    }
}
