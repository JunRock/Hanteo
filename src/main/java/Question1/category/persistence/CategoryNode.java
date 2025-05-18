package Question1.category.persistence;

import Question1.board.persistence.Board;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class CategoryNode {
    public int categoryId;
    public String name;
    public List<CategoryNode> children = new ArrayList<>();
    public List<Board> boards = new ArrayList<>();

    public CategoryNode(int categoryId, String name) {
        this.categoryId = categoryId;
        this.name = name;
    }


    @Override
    public String toString() {
        return String.format("[%d] %s", categoryId, name);
    }
}