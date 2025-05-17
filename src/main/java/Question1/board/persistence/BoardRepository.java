package Question1.board.persistence;

import java.util.List;

public interface BoardRepository {
     void save(int boardId, Board board);

     Board findById(int boardId);

     List<Board> findAll();

}
