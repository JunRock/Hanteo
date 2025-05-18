package Question1.board.domain;

import Question1.board.persistence.Board;
import Question1.board.persistence.BoardEntityRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BoardCreator {
    private final BoardEntityRepository boardRepository;
    public Board create(String boardName) {
        return boardRepository.save(boardName);
    }
}
