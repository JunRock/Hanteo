package Question1.board.persistence;

import java.util.List;

public interface BoardRepository {
     Board save(String boardName);

     Board findById(int boardId);

     List<Board> findAll();

}
