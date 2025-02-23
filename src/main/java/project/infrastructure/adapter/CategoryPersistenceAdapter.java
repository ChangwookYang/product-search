package project.infrastructure.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import project.application.domain.Category;
import project.application.exception.ApiException;
import project.application.port.category.out.CategoryPort;
import project.infrastructure.mapper.CategoryInfraMapper;
import project.infrastructure.repository.CategoryRepository;

import java.util.List;

import static project.application.exception.CategoryErrorType.CATEGORY_NOT_FOUND;

@Repository
@RequiredArgsConstructor
public class CategoryPersistenceAdapter implements CategoryPort {

    private final CategoryRepository categoryRepository;

    private final CategoryInfraMapper mapper;

    @Override
    public Category findByName(String categoryName) {
        return mapper.toDomain(
                categoryRepository.findByName(categoryName).orElseThrow(() -> new ApiException(CATEGORY_NOT_FOUND))
        );
    }

    @Override
    public List<Category> findAll() {
        return mapper.toDomains(categoryRepository.findAll());
    }

    @Override
    public Category findById(Long categoryId) {
        return mapper.toDomain(
                categoryRepository.findById(categoryId).orElseThrow(() -> new ApiException(CATEGORY_NOT_FOUND))
        );
    }
}
