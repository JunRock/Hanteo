package Question1.category.domain;

import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import Question1.category.persistence.CategoryNode;
import Question1.category.persistence.CategoryNodeEntityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CategoryNodeCreatorTest {
    private CategoryNodeEntityRepository mockCategoryNodeRepository;
    private CategoryNodeCreator categoryNodeCreator;

    @BeforeEach
    void setUp(){
        mockCategoryNodeRepository = mock(CategoryNodeEntityRepository.class);
        categoryNodeCreator = new CategoryNodeCreator(mockCategoryNodeRepository);
    }

    @Test
    void create_카테고리_생성_테스트(){
        int categoryId = 1;
        String categoryName = "test";
        CategoryNode categoryNode = new CategoryNode(categoryId,categoryName);

        categoryNodeCreator.create(categoryId, categoryName);

        verify(mockCategoryNodeRepository).save(refEq(categoryNode));
    }

    @Test
    void createLink_카테고리_부모_자식_연결테스트(){
        int parentId = 1;
        int childId = 10;

        categoryNodeCreator.createLink(parentId,childId);

        verify(mockCategoryNodeRepository).link(parentId,childId);
    }
}