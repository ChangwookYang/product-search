package project.application.service.mock;

import project.application.domain.Category;
import project.application.exception.ApiException;
import project.application.port.category.out.CategoryPort;

import java.util.List;

import static project.application.exception.CategoryErrorType.CATEGORY_NOT_FOUND;

public class CategoryTestAdapter implements CategoryPort {

    private final List<Category> categoryList = TestDataFactory.getCategories();

    @Override
    public List<Category> findAll() {
        return categoryList;
    }

    @Override
    public Category findById(Long categoryId) {
        return categoryList.stream().filter(category -> category.id().equals(categoryId)).findFirst()
                .orElseThrow(() -> new ApiException(CATEGORY_NOT_FOUND));
    }

    @Override
    public Category findByName(String categoryName) {
        return categoryList.stream().filter(category -> category.name().equals(categoryName)).findFirst()
                .orElseThrow(() -> new ApiException(CATEGORY_NOT_FOUND));
    }
}
