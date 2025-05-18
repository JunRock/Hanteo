package Question1.board.domain;

import Question1.board.persistence.Board;
import Question1.board.persistence.BoardEntityRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BoardCreator {

    private final BoardEntityRepository boardRepository;
    private final BoardValidator boardValidator;

    public Board create(String boardName) {
        if (boardValidator.isExistsAnonymousBoard()) {
            throw new IllegalArgumentException(
                "이미 존재하는 게시판 이름입니다: " + boardName
            );
        }
        return boardRepository.save(boardName);
    }
}
