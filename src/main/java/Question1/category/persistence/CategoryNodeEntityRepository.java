package Question1.category.persistence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CategoryNodeEntityRepository implements CategoryNodeRepository {

    private final InMemoryCategoryNodeStorage storage;

    @Override
    public CategoryNode save(CategoryNode categoryNode) {
        storage.getNodeMap().put(categoryNode.categoryId, categoryNode);
        storage.getNameIndex().computeIfAbsent(categoryNode.name, node -> new ArrayList<>())
            .add(categoryNode);
        return categoryNode;
    }

    @Override
    public CategoryNode findById(int categoryId) {
        return storage.getNodeMap().get(categoryId);
    }

    @Override
    public List<CategoryNode> findByName(String categoryName) {
        return storage.getNameIndex().getOrDefault(categoryName, Collections.emptyList());
    }

    @Override
    public void link(int parentId, int childId) {
        CategoryNode parent = storage.getNodeMap().get(parentId);
        CategoryNode child = storage.getNodeMap().get(childId);
        if (parent != null && child != null) {
            parent.children.add(child);
        }
    }

    @Override
    public List<CategoryNode> findAll() {
        return new ArrayList<>(storage.getNodeMap().values());
    }
}
