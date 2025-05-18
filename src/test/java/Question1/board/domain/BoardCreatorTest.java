package Question1.board.domain;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import Question1.board.persistence.Board;
import Question1.board.persistence.BoardEntityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BoardCreatorTest {

    private BoardEntityRepository mockBoardRepository;
    private BoardValidator mockBoardValidator;
    private BoardCreator boardCreator;

    @BeforeEach
    void setUp() {
        mockBoardRepository = mock(BoardEntityRepository.class);
        mockBoardValidator = mock(BoardValidator.class);
        boardCreator = new BoardCreator(
            mockBoardRepository,
            mockBoardValidator
        );
    }

    @Test
    void 익명게시판이_존재하지_않는_경우_게시판을_정상적으로_생성후_반환한다() {
        String boardName = "게시판";
        Board board = new Board(1, boardName);
        when(mockBoardValidator.isExistsAnonymousBoard()).thenReturn(false);
        when(mockBoardRepository.save(boardName)).thenReturn(board);

        Board result = boardCreator.create(boardName);

        assertEquals(result, board);
        verify(mockBoardRepository).save(boardName);
        verify(mockBoardValidator).isExistsAnonymousBoard();
    }

    @Test
    void 익명게시판이_존재하는_경우_게시판을_예외를_던진다() {
        String boardName = "익명 게시판";

        when(mockBoardValidator.isExistsAnonymousBoard()).thenReturn(true);

        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> boardCreator.create(boardName)
        );

        assertTrue(exception.getMessage().contains("이미 존재하는 게시판 이름입니다: " + boardName));
        verify(mockBoardValidator).isExistsAnonymousBoard();
        verify(mockBoardRepository, never()).save(any());
    }
}