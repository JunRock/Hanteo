package Question1.util;

import Question1.global.AppConfig.Context;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Init {
    private final Context ctx;

    public void initEnvironment(){
        ctx.categoryRepo.save(ctx.categoryNodeCreator.create(1, "전체"));
        ctx.categoryRepo.save(ctx.categoryNodeCreator.create(2, "공지1"));
        ctx.categoryRepo.save(ctx.categoryNodeCreator.create(3, "공지2"));
        ctx.categoryRepo.save(ctx.categoryNodeCreator.create(4, "공지3"));

        ctx.categoryRepo.link(1, 2);
        ctx.categoryRepo.link(1, 3);
        ctx.categoryRepo.link(2, 4);

        ctx.boardRepo.save(101, ctx.boardCreator.create(101, "공지사항"));
        ctx.boardRepo.save(102, ctx.boardCreator.create(102, "공지사항"));
        ctx.boardRepo.save(201, ctx.boardCreator.create(201, "익명 게시판"));

        ctx.service.assignBoardToCategory(101, 2);
        ctx.service.assignBoardToCategory(102, 3);
        ctx.service.assignBoardToCategory(201, 2);
        ctx.service.assignBoardToCategory(201, 3);
        ctx.service.assignBoardToCategory(201, 4);
    }
}
