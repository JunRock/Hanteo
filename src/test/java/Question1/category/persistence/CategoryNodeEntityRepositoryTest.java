package Question1.category.persistence;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CategoryNodeEntityRepositoryTest {
    private InMemoryCategoryNodeStorage mockStorage;
    private CategoryNodeEntityRepository repository;

    @BeforeEach
    void setUp() {
        mockStorage = new InMemoryCategoryNodeStorage();
        repository = new CategoryNodeEntityRepository(mockStorage);
    }

    @Test
    void save_노드저장_및_조회확인() {
        CategoryNode categoryNode = new CategoryNode(1, "공지");

        repository.save(categoryNode);

        assertEquals(categoryNode, repository.findById(1));
        assertEquals(List.of(categoryNode), repository.findByName("공지"));
    }

    @Test
    void link_부모에_자식추가() {
        CategoryNode parent = new CategoryNode(1, "부모");
        CategoryNode child = new CategoryNode(2, "자식");

        repository.save(parent);
        repository.save(child);

        repository.link(1, 2);

        assertTrue(parent.getChildren().contains(child));
    }

    @Test
    void findAll_전체노드조회() {
        CategoryNode categoryNodeA = new CategoryNode(1, "A");
        CategoryNode categoryNodeB = new CategoryNode(2, "B");

        repository.save(categoryNodeA);
        repository.save(categoryNodeB);

        List<CategoryNode> all = repository.findAll();
        assertEquals(2, all.size());
        assertTrue(all.contains(categoryNodeA));
        assertTrue(all.contains(categoryNodeB));
    }

    @Test
    void existsById_Id_유효성_검증() {
        CategoryNode node = new CategoryNode(10, "FAQ");
        repository.save(node);

        assertTrue(repository.existsById(10));
        assertFalse(repository.existsById(99));
    }

    @Test
    void existsByName_Name_유효성_검증() {
        CategoryNode node = new CategoryNode(20, "공지");
        repository.save(node);

        assertTrue(repository.existsByName("공지"));
        assertFalse(repository.existsByName("없는이름"));
    }
}