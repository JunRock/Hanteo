package Question1.board.persistence;


import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BoardEntityRepository implements BoardRepository {

    private final InMemoryBoardStorage boardStorage;

    @Override
    public Board save(String boardName) {
        int boardId = boardStorage.getNextBoardId();
        Board board = new Board(boardId, boardName);
        boardStorage.getBoardMap().put(boardId, board);
        return board;
    }

    @Override
    public Board findById(int boardId) {
        return Optional.ofNullable(boardStorage.getBoardMap().get(boardId))
            .orElseThrow(() -> new NoSuchElementException("존재하지 않는 게시판입니다."));
    }

    @Override
    public Board findByName(String boardName) {
        return boardStorage.getBoardMap().values().stream()
            .filter(b -> b.getName().equals(boardName))
            .findFirst()
            .orElseThrow(() ->
                new NoSuchElementException("존재하지 않는 게시판 이름입니다: " + boardName)
            );
    }

    @Override
    public boolean existsByName() {
        return boardStorage.getBoardMap()
            .values()
            .stream()
            .anyMatch(b -> b.getName().equals("익명 게시판"));
    }

    @Override
    public List<Board> findAll() {
        return new ArrayList<>(boardStorage.getBoardMap().values());
    }

}
