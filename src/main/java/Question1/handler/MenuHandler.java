package Question1.handler;

import Question1.boardcategory.BoardCategoryService;
import Question1.util.CategoryPrinter;
import lombok.RequiredArgsConstructor;

import java.util.NoSuchElementException;
import java.util.Scanner;

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
        } catch (NumberFormatException e) {
            System.err.println("숫자를 입력해야 합니다.");
        } catch (NoSuchElementException e) {
            System.err.println("해당 카테고리가 없습니다.");
        } catch (Exception e) {
            System.err.println(e.getMessage());
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
            System.out.print("새 카테고리 이름 입력> ");
            String newName = sc.nextLine().trim();
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
}
