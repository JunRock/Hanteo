package Question1.board.persistence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

public class BoardEntityRepository implements BoardRepository {

    private final Map<Integer, Board> boardMap = new HashMap<>();

    @Override
    public void save(int boardId, Board board) {
        boardMap.put(boardId, board);
    }

    @Override
    public Board findById(int boardId) {
        return Optional.ofNullable(boardMap.get(boardId))
            .orElseThrow(() -> new NoSuchElementException("존재하지 않는 게시판입니다."));
    }

    @Override
    public List<Board> findAll() {
        return new ArrayList<>(boardMap.values());
    }

}
