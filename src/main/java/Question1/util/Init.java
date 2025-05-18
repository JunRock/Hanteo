package Question1.util;

import Question1.board.persistence.Board;
import Question1.global.AppConfig.Context;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Init {

    private final Context ctx;

    public void initEnvironment() {
        ctx.service.generateCategory(1, "전체");
        ctx.service.generateCategory(2, "공지1");
        ctx.service.generateCategory(3, "공지2");
        ctx.service.generateCategory(4, "공지3");

        ctx.service.linkCategory(1, 2);
        ctx.service.linkCategory(1, 3);
        ctx.service.linkCategory(2, 4);

        Board board1 = ctx.service.createBoard("공지사항");
        Board board2 = ctx.service.createBoard("공지사항");
        Board board3 = ctx.service.createBoard("익명 게시판");

        ctx.service.assignBoardToCategory(board1, 2);
        ctx.service.assignBoardToCategory(board2, 3);
        ctx.service.assignBoardToCategory(board3, 2);
        ctx.service.assignBoardToCategory(board3, 3);
        ctx.service.assignBoardToCategory(board3, 4);
    }
}
