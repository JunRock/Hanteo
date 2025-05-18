package Question1.global;

import Question1.board.domain.BoardCreator;
import Question1.board.persistence.BoardEntityRepository;
import Question1.board.persistence.InMemoryBoardStorage;
import Question1.boardcategory.BoardCategoryService;
import Question1.category.domain.CategoryNodeCreator;
import Question1.category.domain.CategoryNodeValidator;
import Question1.category.persistence.CategoryNodeEntityRepository;
import Question1.handler.MenuHandler;
import Question1.util.CategoryPrinter;
import Question1.util.JsonPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Scanner;

public class AppConfig {

    public static class Context {

        public final Scanner sc;
        public final BoardEntityRepository boardRepo;
        public final CategoryNodeEntityRepository categoryRepo;
        public final CategoryNodeCreator categoryNodeCreator;
        public final BoardCreator boardCreator;
        public final BoardCategoryService service;
        public final CategoryPrinter categoryPrinter;
        public final MenuHandler handler;

        private Context(
            Scanner sc,
            BoardEntityRepository boardRepo,
            CategoryNodeEntityRepository categoryRepo,
            CategoryNodeCreator categoryNodeCreator,
            BoardCreator boardCreator,
            BoardCategoryService service,
            CategoryPrinter categoryPrinter,
            MenuHandler handler
        ) {
            this.sc = sc;
            this.boardRepo = boardRepo;
            this.categoryRepo = categoryRepo;
            this.categoryNodeCreator = categoryNodeCreator;
            this.boardCreator = boardCreator;
            this.service = service;
            this.categoryPrinter = categoryPrinter;
            this.handler = handler;
        }
    }

    public static Context createContext() {
        Scanner sc = new Scanner(System.in);
        var storage = new InMemoryBoardStorage();

        var boardRepo = new BoardEntityRepository(storage);
        var categoryRepo = new CategoryNodeEntityRepository();
        var validator = new CategoryNodeValidator(categoryRepo);
        var categoryNodeCreator = new CategoryNodeCreator(categoryRepo, validator);
        var boardCreator = new BoardCreator(boardRepo);

        var mapper = new ObjectMapper();
        var jsonPrinter = new JsonPrinter(mapper);
        var service = new BoardCategoryService(
            categoryRepo,
            jsonPrinter,
            categoryNodeCreator,
            boardCreator
        );
        var categoryPrinter = new CategoryPrinter(categoryRepo);

        var handler = new MenuHandler(sc, service, categoryPrinter);

        return new Context(
            sc,
            boardRepo,
            categoryRepo,
            categoryNodeCreator,
            boardCreator,
            service,
            categoryPrinter,
            handler
        );
    }
}
