package project.web.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import project.application.dto.result.ProductResult;
import project.web.dto.response.ProductResponse;

@Mapper(componentModel = "spring")
public abstract class ProductSearchMapper {

    @Mapping(target = "categoryName", source = "result.category.name")
    @Mapping(target = "brandName", source = "result.brand.name")
    public abstract ProductResponse toResponse(ProductResult result);
}
