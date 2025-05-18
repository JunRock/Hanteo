package Question1.board.persistence;


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
    public boolean existsByName() {
        return boardStorage.getBoardMap()
            .values()
            .stream()
            .anyMatch(b -> b.getName().equals("익명 게시판"));
    }

}
