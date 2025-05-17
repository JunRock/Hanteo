package Question1.util;

import Question1.category.persistence.CategoryNodeEntityRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CategoryPrinter {

    private final CategoryNodeEntityRepository categoryNodeRepository;

    public void getAllCategory() {
        System.out.println("[ 현재 카테고리 List ]");
        categoryNodeRepository.findAll()
            .forEach(it -> System.out.println("" + it));
    }
}
