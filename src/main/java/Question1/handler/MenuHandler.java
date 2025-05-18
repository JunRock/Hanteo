package Question1.handler;

import Question1.board.persistence.Board;
import Question1.boardcategory.BoardCategoryService;
import Question1.util.CategoryPrinter;
import java.util.Scanner;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MenuHandler {

    private final Scanner sc;
    private final BoardCategoryService service;
    private final CategoryPrinter categoryPrinter;

    @FunctionalInterface
    private interface Action {
        void run() throws Exception;
    }

    private void exec(Action action) {
        try {
            action.run();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void handleIdLookup() {
        exec(() -> {
            categoryPrinter.getAllCategory();
            System.out.print("조회할 카테고리 ID 입력> ");
            int id = Integer.parseInt(sc.nextLine().trim());
            String json = service.toJsonSubTree(id);
            System.out.println("\n▶ 서브트리 JSON:\n" + json);
        });
    }

    public void handleNameLookup() {
        exec(() -> {
            categoryPrinter.getAllCategory();
            System.out.print("조회할 카테고리 이름 입력> ");
            String name = sc.nextLine().trim();
            String json = service.toJsonTreeList(name);
            System.out.println("\n▶ 서브트리 JSON:\n" + json);
        });
    }

    public void handleCreateCategory() {
        exec(() -> {
            System.out.print("새 카테고리 ID 입력> ");
            int newId = Integer.parseInt(sc.nextLine().trim());
            service.validCategoryId(newId);
            System.out.print("새 카테고리 이름 입력> ");
            String newName = sc.nextLine();
            service.validCategoryName(newName);
            service.generateCategory(newId, newName);

            System.out.print("부모 카테고리 ID 입력(없으면 엔터)> ");
            String parent = sc.nextLine().trim();
            if (!parent.isEmpty()) {
                int parentId = Integer.parseInt(parent);
                service.linkCategory(parentId, newId);
                System.out.println("  ↳ 부모 " + parentId + "에 연결 완료");
            }
            System.out.println("✔ 카테고리 생성 완료: [" + newId + "] " + newName);
        });
    }
    public void handleCreateBoard(){
        exec(()->{
            System.out.print("게시글 제목 입력> ");
            String boardName = sc.nextLine();
            Board board = service.createBoard(boardName);

            categoryPrinter.getAllCategory();
            System.out.print("하위 카테고리 ID 입력> ");
            int categoryId = Integer.parseInt(sc.nextLine().trim());
            service.assignBoardToCategory(board,categoryId);
        });
    }
}
