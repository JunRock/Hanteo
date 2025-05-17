package Question1.board.persistence;

import Question1.category.persistence.CategoryNode;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class Board {
    private final int boardId;
    private final String name;

    @JsonIgnore
    private final List<CategoryNode> categories = new ArrayList<>();

    public Board(int boardId, String name) {
        this.boardId = boardId;
        this.name = name;
    }
}
