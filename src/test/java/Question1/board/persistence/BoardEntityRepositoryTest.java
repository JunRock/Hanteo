package Question1.board.persistence;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BoardEntityRepositoryTest {

    private InMemoryBoardStorage mockStorage;
    private BoardEntityRepository boardRepository;

    @BeforeEach
    void setUp() {
        mockStorage = new InMemoryBoardStorage();
        boardRepository = new BoardEntityRepository(mockStorage);
    }

    @Test
    void save_게시판이_정상적으로_저장된다() {
        Board board = boardRepository.save("게시판");

        assertEquals("게시판", board.getName());
        assertEquals(board, mockStorage.getBoardMap().get(board.getBoardId()));
    }

    @Test
    void existsByName_익명게시판이_존재할때_true를_반환한다() {
        boardRepository.save("익명 게시판");

        assertTrue(boardRepository.existsByName());
    }

    @Test
    void existsByName_익명게시판이_없으면_false를_반환한다() {
        boardRepository.save("공지사항");
        boardRepository.save("자유게시판");

        assertFalse(boardRepository.existsByName());
    }
}