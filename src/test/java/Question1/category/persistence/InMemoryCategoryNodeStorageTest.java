package Question1.category.persistence;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InMemoryCategoryNodeStorageTest {

    private InMemoryCategoryNodeStorage storage;

    @BeforeEach
    void setUp() {
        storage = new InMemoryCategoryNodeStorage();
    }

    @Test
    void nodeMap에_카테고리를_저장하고_조회할_수_있다() {
        CategoryNode node = new CategoryNode(1, "공지");
        storage.getNodeMap().put(node.getCategoryId(), node);

        CategoryNode result = storage.getNodeMap().get(1);
        assertNotNull(result);
        assertEquals("공지", result.getName());
    }

    @Test
    void nameIndex에_이름기반으로_여러_노드를_저장하고_조회할_수_있다() {
        CategoryNode a = new CategoryNode(2, "공지");
        CategoryNode b = new CategoryNode(3, "공지");

        storage.getNameIndex().put("공지", List.of(a, b));

        List<CategoryNode> result = storage.getNameIndex().get("공지");

        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains(a));
        assertTrue(result.contains(b));
    }

    @Test
    void 없는_ID나_이름을_조회하면_null_or_empty를_반환한다() {
        assertNull(storage.getNodeMap().get(4));
        assertNull(storage.getNameIndex().get("없는이름"));
    }
}