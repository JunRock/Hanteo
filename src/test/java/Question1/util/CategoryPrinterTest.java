package Question1.util;

import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import Question1.category.persistence.CategoryNode;
import Question1.category.persistence.CategoryNodeEntityRepository;
import java.util.List;
import org.junit.jupiter.api.Test;

class CategoryPrinterTest {
    @Test
    void getAllCategory_출력확인() throws Exception {
        CategoryNodeEntityRepository mockRepo = mock(CategoryNodeEntityRepository.class);

        List<CategoryNode> dummyList = List.of(
            new CategoryNode(1, "공지"),
            new CategoryNode(2, "익명")
        );
        when(mockRepo.findAll()).thenReturn(dummyList);

        CategoryPrinter printer = new CategoryPrinter(mockRepo);

        String output = tapSystemOut(printer::getAllCategory);

        assertTrue(output.contains("[ 현재 카테고리 List ]"));
        assertTrue(output.contains("공지"));
        assertTrue(output.contains("익명"));
    }
}