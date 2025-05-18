package Question1.board.persistence;

import java.util.List;

public interface BoardRepository {
     Board save(String boardName);

     Board findById(int boardId);

     Board findByName(String boardName);

     List<Board> findAll();

    boolean existsByName();
}
