package Question1.board.domain;

import Question1.board.persistence.BoardEntityRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BoardValidator {
    private final BoardEntityRepository boardRepository;

    public boolean isExistsAnonymousBoard(){
        return boardRepository.existsByName();
    }
}
