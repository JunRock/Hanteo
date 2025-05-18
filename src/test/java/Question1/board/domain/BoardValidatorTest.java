package Question1.board.domain;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import Question1.board.persistence.BoardEntityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BoardValidatorTest {
    private BoardEntityRepository mockBoardRepository;
    private BoardValidator validator;
    @BeforeEach
    void setUp() {
        mockBoardRepository = mock(BoardEntityRepository.class);
        validator = new BoardValidator(mockBoardRepository);
    }

    @Test
    void 익명게시판인_경우_true_를_반환한다(){
        when(mockBoardRepository.existsByName()).thenReturn(true);

        boolean result = validator.isExistsAnonymousBoard();

        assertTrue(result);
    }

    @Test
    void 익명게시판인_아닌_경우_false_를_반환한다(){
        when(mockBoardRepository.existsByName()).thenReturn(false);

        boolean result = validator.isExistsAnonymousBoard();

        assertFalse(result);
    }
}