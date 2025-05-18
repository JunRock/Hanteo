package Question1.util;

import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class MenuPrinterTest {

    @Test
    void printMenu_출력확인() throws Exception {
        String output = tapSystemOut(MenuPrinter::printMenu);

        assertTrue(output.contains("===== 게시판 카테고리 조회 메뉴 ====="));
        assertTrue(output.contains("1. 카테고리 ID로 조회"));
        assertTrue(output.contains("2. 카테고리 이름으로 조회"));
        assertTrue(output.contains("3. 카테고리 추가"));
        assertTrue(output.contains("4. 게시판 생성"));
        assertTrue(output.contains("5. 종료"));
    }

    @Test
    void printPrompt_출력확인() throws Exception {
        String output = tapSystemOut(MenuPrinter::printPrompt);

        assertTrue(output.contains("선택>"));
    }
}