package Question1.category.persistence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryNodeEntityRepository implements CategoryNodeRepository {

    private final Map<Integer, CategoryNode> nodeMap = new HashMap<>();
    private final Map<String, List<CategoryNode>> nameIndex = new HashMap<>();

    @Override
    public CategoryNode save(CategoryNode categoryNode) {
        nodeMap.put(categoryNode.categoryId, categoryNode);
        nameIndex.computeIfAbsent(categoryNode.name, node -> new ArrayList<>()).add(categoryNode);
        return categoryNode;
    }

    @Override
    public CategoryNode findById(int categoryId) {
        return nodeMap.get(categoryId);
    }

    @Override
    public List<CategoryNode> findByName(String categoryName) {
        return nameIndex.getOrDefault(categoryName, Collections.emptyList());
    }

    @Override
    public void link(int parentId, int childId) {
        CategoryNode parent = nodeMap.get(parentId);
        CategoryNode child = nodeMap.get(childId);
        if (parent != null && child != null) {
            parent.children.add(child);
        }
    }

    @Override
    public List<CategoryNode> findAll() {
        return new ArrayList<>(nodeMap.values());
    }
}
