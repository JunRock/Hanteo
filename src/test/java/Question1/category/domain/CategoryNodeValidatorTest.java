package Question1.category.domain;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import Question1.category.persistence.CategoryNodeEntityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CategoryNodeValidatorTest {

    private CategoryNodeEntityRepository mockCategoryNodeRepository;
    private CategoryNodeValidator categoryNodeValidator;

    @BeforeEach
    void setUp() {
        mockCategoryNodeRepository = mock(CategoryNodeEntityRepository.class);
        categoryNodeValidator = new CategoryNodeValidator(mockCategoryNodeRepository);
    }

    @Test
    void 존재하지_않는_ID는_예외없이_통과한다() {
        int categoryId = 1;
        when(mockCategoryNodeRepository.existsById(categoryId)).thenReturn(false);

        assertDoesNotThrow(() -> categoryNodeValidator.isExistCategoryId(categoryId));
    }

    @Test
    void 존재하는_ID는_예외를_발생시킨다() {
        int categoryId = 1;

        when(mockCategoryNodeRepository.existsById(categoryId)).thenReturn(true);

        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> categoryNodeValidator.isExistCategoryId(categoryId)
        );

        assertTrue(exception.getMessage().contains("해당 카테고리 ID 존재: " + categoryId));
    }

    @Test
    void 존재하지_않는_이름은_예외없이_통과한다() {
        String categoryName = "test";

        when(mockCategoryNodeRepository.existsByName(categoryName)).thenReturn(false);

        assertDoesNotThrow(() -> categoryNodeValidator.isExistCategoryName(categoryName));
    }

    @Test
    void 존재하는_이름은_예외를_발생시킨다() {
        String categoryName = "test";

        when(mockCategoryNodeRepository.existsByName(categoryName)).thenReturn(true);

        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> categoryNodeValidator.isExistCategoryName(categoryName)
        );

        assertTrue(exception.getMessage().contains("해당 카테고리 이름 존재: " + categoryName));
    }
}