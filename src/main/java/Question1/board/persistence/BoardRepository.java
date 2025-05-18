package Question1.board.persistence;

public interface BoardRepository {
     Board save(String boardName);

    boolean existsByName();
}
