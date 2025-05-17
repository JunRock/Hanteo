package Question1.board.domain;

import Question1.board.persistence.Board;

public class BoardCreator {
    public Board create(int boardId, String boardName) {
        return new Board(boardId,boardName);
    }
}
