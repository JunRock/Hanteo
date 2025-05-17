package Question1.category.domain;

import Question1.category.persistence.CategoryNode;
import Question1.category.persistence.CategoryNodeEntityRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CategoryNodeValidator {
    private final CategoryNodeEntityRepository categoryNodeRepository;

    public void validateNew(CategoryNode node) {
        if (categoryNodeRepository.existsById(node.getCategoryId())) {
            throw new IllegalArgumentException(
                "해당 카테고리 ID 존재: " + node.getCategoryId()
            );
        }
        if (categoryNodeRepository.existsByName(node.getName())) {
            throw new IllegalArgumentException(
                "해당 카테고리 이름 존재: " + node.getName()
            );
        }
    }
}
