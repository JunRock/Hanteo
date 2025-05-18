package Question1.category.domain;

import Question1.category.persistence.CategoryNode;
import Question1.category.persistence.CategoryNodeEntityRepository;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class CategoryNodeCreator {

    private final CategoryNodeEntityRepository categoryNodeRepository;

    public void create(int categoryId, String categoryName) {
        CategoryNode categoryNode = new CategoryNode(categoryId, categoryName);
        categoryNodeRepository.save(categoryNode);
    }

    public void createLink(int parentId, int childId){
        categoryNodeRepository.link(parentId, childId);
    }
}
