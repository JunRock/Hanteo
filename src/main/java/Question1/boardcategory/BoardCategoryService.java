package Question1.boardcategory;

import Question1.board.domain.BoardCreator;
import Question1.board.persistence.Board;
import Question1.category.domain.CategoryNodeCreator;
import Question1.category.persistence.CategoryNode;
import Question1.category.persistence.CategoryNodeEntityRepository;
import Question1.util.JsonPrinter;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BoardCategoryService {

    private final CategoryNodeEntityRepository categoryNodeRepository;
    private final JsonPrinter jsonPrinter;
    private final CategoryNodeCreator categoryNodeCreator;
    private final BoardCreator boardCreator;
    private static final String DELIMITER = "\n\n";

    public void assignBoardToCategory(Board board, int categoryId) {
        CategoryNode category = categoryNodeRepository.findById(categoryId);

        board.getCategories().add(category);
        category.boards.add(board);
    }

    public String toJsonSubTree(int categoryId) {
        CategoryNode node = categoryNodeRepository.findById(categoryId);
        return jsonPrinter.toJson(node);
    }

    public String toJsonTreeList(String categoryName) {
        List<CategoryNode> categoryNodeList = categoryNodeRepository.findByName(categoryName);
        return categoryNodeList.stream()
            .map(jsonPrinter::toJson)
            .collect(Collectors.joining(DELIMITER));
    }

    public void generateCategory(int categoryId, String categoryName) {
        categoryNodeCreator.create(categoryId, categoryName);
    }

    public void linkCategory(int parentId, int childId) {
        categoryNodeCreator.createLink(parentId, childId);
    }

    public Board createBoard(String boardName) {
        return boardCreator.create(boardName);
    }
}
