package project.infrastructure.mapper;

import org.mapstruct.Mapper;
import project.application.domain.Category;
import project.infrastructure.entity.CategoryEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class CategoryInfraMapper implements InfraMapper<Category, CategoryEntity> {
    public abstract List<Category> toDomains(List<CategoryEntity> entities);
}
