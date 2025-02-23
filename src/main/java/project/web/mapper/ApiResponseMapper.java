package project.web.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import project.application.dto.result.CheapestAndPriciestBrandOfCategory;
import project.application.dto.result.CheapestBrandInfoListInAllCategories;
import project.application.dto.result.CheapestProductInfoListInCategory;
import project.application.dto.result.ProductResult;
import project.web.dto.response.Api1Response;
import project.web.dto.response.Api2Response;
import project.web.dto.response.Api3Response;
import project.web.dto.response.ProductResponse;

@Mapper(componentModel = "spring")
public abstract class ApiResponseMapper {

    @Named("toResponse1")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "brandName", source = "brand.name")
    @Mapping(target = "categoryName", source = "category.name")
    abstract public ProductResponse toResponse1(ProductResult productResult);

    @Named("toResponse2")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "brandName", ignore = true)
    @Mapping(target = "categoryName", source = "category.name")
    abstract public ProductResponse toResponse2(ProductResult productResult);

    @Named("toResponse3")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "brandName", source = "brand.name")
    @Mapping(target = "categoryName", ignore = true)
    abstract public ProductResponse toResponse3(ProductResult productResult);

    @Mapping(target = "productList", source = "productList", qualifiedByName = "toResponse1")
    abstract public Api1Response toResponse(CheapestProductInfoListInCategory source);

    @Mapping(target = "brandName", source = "brand.name")
    @Mapping(target = "categoryList", source = "productList", qualifiedByName = "toResponse2")
    abstract public Api2Response toResponse(CheapestBrandInfoListInAllCategories source);

    @Mapping(target = "categoryName", source = "category.name")
    @Mapping(target = "cheapestProduct", source = "cheapestProduct", qualifiedByName = "toResponse3")
    @Mapping(target = "priciestProduct", source = "priciestProduct", qualifiedByName = "toResponse3")
    abstract public Api3Response toResponse(CheapestAndPriciestBrandOfCategory source);
}
