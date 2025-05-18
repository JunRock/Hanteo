package Question1.boardcategory;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import Question1.board.domain.BoardCreator;
import Question1.board.persistence.Board;
import Question1.category.domain.CategoryNodeCreator;
import Question1.category.domain.CategoryNodeValidator;
import Question1.category.persistence.CategoryNode;
import Question1.category.persistence.CategoryNodeEntityRepository;
import Question1.util.JsonPrinter;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BoardCategoryServiceTest {

    private CategoryNodeEntityRepository mockCategoryNodeRepository;
    private JsonPrinter mockJsonPrinter;
    private CategoryNodeCreator mockCategoryNodeCreator;
    private BoardCreator mockBoardCreator;
    private CategoryNodeValidator mockValidator;
    private BoardCategoryService service;
    @BeforeEach
    void setUp() {
        mockCategoryNodeRepository = mock(CategoryNodeEntityRepository.class);
        mockJsonPrinter = mock(JsonPrinter.class);
        mockCategoryNodeCreator = mock(CategoryNodeCreator.class);
        mockBoardCreator = mock(BoardCreator.class);
        mockValidator = mock(CategoryNodeValidator.class);
        service = new BoardCategoryService(
            mockCategoryNodeRepository,
            mockJsonPrinter,
            mockCategoryNodeCreator,
            mockBoardCreator,
            mockValidator
        );
    }

    @Test
    void assignBoardToCategory_게시판과_카테고리에_서로_추가된다() {
        Board board = new Board(1,"게시판1");
        CategoryNode categoryNode = new CategoryNode(1, "카테고리");
        int categoryId = 1;

        when(mockCategoryNodeRepository.findById(categoryId)).thenReturn(categoryNode);

        service.assignBoardToCategory(board, 1);
        assertTrue(board.getCategories().contains(categoryNode));
        assertTrue(categoryNode.getBoards().contains(board));
    }

    @Test
    void toJsonSubTree_카테고리_객체를_JSON문자열로_반환한다(){
        CategoryNode categoryNode = new CategoryNode(1, "카테고리");
        int categoryId = 1;

        when(mockCategoryNodeRepository.findById(categoryId)).thenReturn(categoryNode);
        when(mockJsonPrinter.toJson(categoryNode)).thenReturn("{\"id\":1,\"name\":\"카테고리\"}");

        String result = service.toJsonSubTree(categoryId);

        verify(mockCategoryNodeRepository).findById(categoryId);
        verify(mockJsonPrinter).toJson(categoryNode);
        assertEquals("{\"id\":1,\"name\":\"카테고리\"}", result);
    }

    @Test
    void toJsonTreeList_카테고리이름으로_여러_노드를_JSON으로_변환한다() {
        CategoryNode a = new CategoryNode(1, "공지");
        CategoryNode b = new CategoryNode(2, "공지");

        when(mockCategoryNodeRepository.findByName("공지")).thenReturn(List.of(a, b));
        when(mockJsonPrinter.toJson(a)).thenReturn("{\"id\":1}");
        when(mockJsonPrinter.toJson(b)).thenReturn("{\"id\":2}");

        String result = service.toJsonTreeList("공지");

        assertEquals("{\"id\":1}\n\n{\"id\":2}", result);
    }

    @Test
    void generateCategory_호출시_creator에_create_요청한다() {
        int categoryId = 1;
        String categoryName = "test";

        service.generateCategory(categoryId,categoryName);

        verify(mockCategoryNodeCreator).create(categoryId,categoryName);
    }

    @Test
    void linkCategory_호출시_creator에_link_요청한다() {
        int parentId = 1;
        int childId = 2;

        service.linkCategory(parentId, childId);

        verify(mockCategoryNodeCreator).createLink(parentId, childId);
    }

    @Test
    void createBoard_호출시_boardCreator_호출후_리턴된다() {
        Board board = new Board(1, "게시판");
        when(mockBoardCreator.create("게시판")).thenReturn(board);

        Board result = service.createBoard("게시판");

        assertEquals(board, result);
        verify(mockBoardCreator).create("게시판");
    }

    @Test
    void validCategoryId_호출시_validator에_isExistCategoryId_요청한다() {
        service.validCategoryId(1);

        verify(mockValidator).isExistCategoryId(1);
    }

    @Test
    void validCategoryName_호출시_validator에_isExistCategoryName_요청한다() {
        service.validCategoryName("카테고리");

        verify(mockValidator).isExistCategoryName("카테고리");
    }
}