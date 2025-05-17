package Question1.category.persistence;

import java.util.List;

public interface CategoryNodeRepository {

    CategoryNode save(CategoryNode categoryNode);

    CategoryNode findById(int categoryId);

    List<CategoryNode> findByName(String categoryName);

    void link(int parentId, int childId);

    List<CategoryNode> findAll();

    default boolean existsById(int categoryId){
        return findById(categoryId) != null;
    }

    default boolean existsByName(String categoryName){
        return !findByName(categoryName).isEmpty();
    }

}
