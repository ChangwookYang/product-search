package project.application.mapper;

import org.mapstruct.Mapper;
import project.application.domain.Category;
import project.application.dto.result.CategoryResult;

@Mapper(componentModel = "spring")
public abstract class CategoryAppMapper implements AppResultMapper<Category, CategoryResult> {
}
