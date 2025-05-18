package Question1.board.persistence;

import java.util.HashMap;
import java.util.Map;
import lombok.Getter;

@Getter
public class InMemoryBoardStorage {
    private final Map<Integer, Board> boardMap = new HashMap<>();

    public int getNextBoardId(){
        return boardMap.keySet().stream()
            .mapToInt(Integer::intValue)
            .max()
            .orElse(0) + 1;
    }
}
