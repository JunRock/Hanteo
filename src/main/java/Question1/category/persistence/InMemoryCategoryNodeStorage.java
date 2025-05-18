package Question1.category.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Getter;

@Getter
public class InMemoryCategoryNodeStorage {
    private final Map<Integer, CategoryNode> nodeMap = new HashMap<>();
    private final Map<String, List<CategoryNode>> nameIndex = new HashMap<>();
}
