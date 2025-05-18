package Question1.category.persistence;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CategoryNodeTest {
    @Test
    void 생성자_및_toString_테스트() {
        CategoryNode node = new CategoryNode(1, "공지");

        assertEquals(1, node.getCategoryId());
        assertEquals("공지", node.getName());
        assertEquals("[" + node.getCategoryId() + "] " + node.getName(), node.toString());
    }

    @Test
    void 초기_children과_boards는_비어있다() {
        CategoryNode node = new CategoryNode(1, "공지");

        assertEquals(0, node.getChildren().size());
        assertEquals(0, node.getBoards().size());
    }
}