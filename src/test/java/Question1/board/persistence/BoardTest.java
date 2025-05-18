package Question1.board.persistence;

import static org.junit.jupiter.api.Assertions.*;

import Question1.category.persistence.CategoryNode;
import org.junit.jupiter.api.Test;

class BoardTest {
    @Test
    void 생성자와_getter가_정상적으로_동작한다() {
        Board board = new Board(1, "게시판");

        assertEquals(1, board.getBoardId());
        assertEquals("게시판", board.getName());
        assertTrue(board.getCategories().isEmpty());
    }

    @Test
    void 카테고리_추가가_정상적으로_되어야한다() {
        Board board = new Board(1, "게시판");
        CategoryNode category = new CategoryNode(1, "카테고리");

        board.getCategories().add(category);

        assertEquals(1, board.getCategories().size());
        assertEquals("카테고리", board.getCategories().get(0).getName());
    }
}