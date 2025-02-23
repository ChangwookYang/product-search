package project.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.application.port.product.in.ProductApiUseCase;
import project.web.dto.response.Api1Response;
import project.web.dto.response.Api2Response;
import project.web.dto.response.Api3Response;
import project.web.mapper.ApiResponseMapper;

@Tag(name = "유형별 상품 조회 API")
@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductApiController {

    private final ProductApiUseCase productApiUseCase;
    private final ApiResponseMapper responseMapper;

    @Operation(
            summary = "카테고리 별 최저 가격 조회",
            description = "카테고리 별 최저가격 브랜드와 상품 가격, 총액을 조회하는 API")
    @GetMapping(value = "/cheapest-by-category")
    private Api1Response findCheapestProductInfoByCategory() {
        return responseMapper.toResponse(productApiUseCase.findCheapestProductInfoByCategory());
    }

    @Operation(
            summary = "단일 브랜드로 전체 카테고리 상품 최저 가격 조회",
            description = "단일 브랜드로 전체 카테고리 상품을 구매할 경우 최저가격인 브랜드와 총액을 조회하는 API")
    @GetMapping(value = "/categories/cheapest-brand")
    private Api2Response findCheapestBrandInAllCategories() {
        return responseMapper.toResponse(productApiUseCase.findCheapestBrandInAllCategories());
    }

    @Operation(
            summary = "특정 카테고리에서 최저가격 브랜드와 최고가격 브랜드 조회",
            description = "특정 카테고리에서 최저가격 브랜드와 최고가격 브랜드를 확인하고 각 브랜드 상품의 가격을 확인하는 API")
    @GetMapping(value = "/cheapest-and-priciest")
    private Api3Response findCheapestAndPriciestBrandInCategory(@RequestParam String categoryName) {
        return responseMapper.toResponse(productApiUseCase.findCheapestAndPriciestBrandInCategory(categoryName));
    }
}