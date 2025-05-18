package Question1.handler;

import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import Question1.board.persistence.Board;
import Question1.boardcategory.BoardCategoryService;
import Question1.util.CategoryPrinter;
import java.io.ByteArrayInputStream;
import java.util.Scanner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MenuHandlerTest {

    private BoardCategoryService mockService;
    private CategoryPrinter mockPrinter;

    @BeforeEach
    void setUp() {
        mockService = mock(BoardCategoryService.class);
        mockPrinter = mock(CategoryPrinter.class);
    }

    @Test
    void handleCreateCategory_카테고리_생성과_부모연결() {
        String input = "10\nTEST\n1\n";
        Scanner sc = new Scanner(new ByteArrayInputStream(input.getBytes()));
        MenuHandler handler = new MenuHandler(sc, mockService, mockPrinter);

        handler.handleCreateCategory();

        verify(mockService).validCategoryId(10);
        verify(mockService).validCategoryName("TEST");
        verify(mockService).generateCategory(10, "TEST");
        verify(mockService).linkCategory(1, 10);
    }

    @Test
    void handleCreateBoard_게시판생성후_카테고리연결() {
        String input = "공지사항\n2\n";
        Scanner sc = new Scanner(new ByteArrayInputStream(input.getBytes()));
        MenuHandler handler = new MenuHandler(sc, mockService, mockPrinter);

        Board board = new Board(1, "공지사항");
        when(mockService.createBoard("공지사항")).thenReturn(board);

        handler.handleCreateBoard();

        verify(mockService).createBoard("공지사항");
        verify(mockService).assignBoardToCategory(board, 2);
        verify(mockPrinter).getAllCategory();
    }

    @Test
    void handleIdLookup_카테고리ID로_조회() throws Exception {
        String input = "2\n";
        Scanner sc = new Scanner(new ByteArrayInputStream(input.getBytes()));
        MenuHandler handler = new MenuHandler(sc, mockService, mockPrinter);

        when(mockService.toJsonSubTree(2)).thenReturn("{\"id\":2}");

        String output = tapSystemOut(handler::handleIdLookup);

        verify(mockPrinter).getAllCategory();
        verify(mockService).toJsonSubTree(2);
        assertTrue(output.contains("▶ 서브트리 JSON:"));
        assertTrue(output.contains("{\"id\":2}"));
    }

    @Test
    void handleNameLookup_카테고리이름으로_조회() throws Exception {
        String input = "공지\n";
        Scanner sc = new Scanner(new ByteArrayInputStream(input.getBytes()));
        MenuHandler handler = new MenuHandler(sc, mockService, mockPrinter);

        when(mockService.toJsonTreeList("공지")).thenReturn("[{\"name\":\"공지\"}]");
        String output = tapSystemOut(handler::handleNameLookup);

        verify(mockPrinter).getAllCategory();
        verify(mockService).toJsonTreeList("공지");
        assertTrue(output.contains("▶ 서브트리 JSON:"));
        assertTrue(output.contains("[{\"name\":\"공지\"}]"));
    }
}