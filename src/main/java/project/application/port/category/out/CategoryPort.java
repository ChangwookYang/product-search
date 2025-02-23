package project.application.port.category.out;

import project.application.domain.Category;

import java.util.List;

public interface CategoryPort {
    List<Category> findAll();

    Category findById(Long categoryId);

    Category findByName(String categoryName);
}
