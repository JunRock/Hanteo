package Question1.util;

import Question1.board.persistence.Board;
import Question1.global.AppConfig.Context;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Init {

    private final Context ctx;

    public void initEnvironment() {
        ctx.categoryRepo.save(ctx.categoryNodeCreator.create(1, "전체"));
        ctx.categoryRepo.save(ctx.categoryNodeCreator.create(2, "공지1"));
        ctx.categoryRepo.save(ctx.categoryNodeCreator.create(3, "공지2"));
        ctx.categoryRepo.save(ctx.categoryNodeCreator.create(4, "공지3"));

        ctx.categoryRepo.link(1, 2);
        ctx.categoryRepo.link(1, 3);
        ctx.categoryRepo.link(2, 4);

        Board board1 = ctx.boardCreator.create("공지사항");
        Board board2 = ctx.boardCreator.create("공지사항");
        Board board3 = ctx.boardCreator.create("익명 게시판");

        ctx.service.assignBoardToCategory(board1, 2);
        ctx.service.assignBoardToCategory(board2, 3);
        ctx.service.assignBoardToCategory(board3, 2);
        ctx.service.assignBoardToCategory(board3, 3);
        ctx.service.assignBoardToCategory(board3, 4);
    }
}
