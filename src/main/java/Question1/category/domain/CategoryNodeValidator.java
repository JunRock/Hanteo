package Question1.category.domain;

import Question1.category.persistence.CategoryNodeEntityRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CategoryNodeValidator {
    private final CategoryNodeEntityRepository categoryNodeRepository;

    public void isExistCategoryId(int categoryId){
        if (categoryNodeRepository.existsById(categoryId)) {
            throw new IllegalArgumentException(
                "해당 카테고리 ID 존재: " + categoryId
            );
        }
    }

    public void isExistCategoryName(String categoryName){
        if (categoryNodeRepository.existsByName(categoryName)) {
            throw new IllegalArgumentException(
                "해당 카테고리 이름 존재: " + categoryName
            );
        }
    }
}
