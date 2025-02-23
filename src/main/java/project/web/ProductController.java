package project.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import project.application.port.product.in.ProductUseCase;
import project.web.dto.request.ProductCreateRequest;
import project.web.dto.request.ProductUpdateRequest;
import project.web.dto.response.ProductResponse;
import project.web.mapper.ProductSearchMapper;
import project.web.mapper.ProductUpsertMapper;

@Tag(name = "상품 추가/업데이트/삭제 API")
@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductUseCase productUseCase;
    private final ProductUpsertMapper upsertMapper;
    private final ProductSearchMapper productMapper;

    @Operation(summary = "Product 조회", description = "Product 조회")
    @GetMapping(value = "/{productId}")
    public ProductResponse findById(
            @Parameter(name = "productId", description = "Product Id", example = "1", required = true)
            @PathVariable Long productId) {
        return productMapper.toResponse(productUseCase.findById(productId));
    }

    @Operation(
            summary = "Product 생성",
            description = "상품 정보를 입력해주세요.(브랜드, 카테고리 당 하나의 상품만 생성가능하여 브랜드 혹은 카테고리 먼저 생성필요)")
    @PostMapping
    public ProductResponse create(@RequestBody ProductCreateRequest request) {
        return productMapper.toResponse(productUseCase.create(upsertMapper.toCommand(request)));
    }

    @Operation(summary = "Product 업데이트", description = "변경할 상품 가격을 입력해주세요.")
    @PatchMapping(value = "/{productId}")
    public ProductResponse update(
            @Parameter(name = "productId", description = "Product Id", example = "1", required = true)
            @PathVariable Long productId,
            @RequestBody ProductUpdateRequest request) {
        return productMapper.toResponse(productUseCase.update(productId, request.price()));
    }

    @Operation(summary = "Product 삭제", description = "상품 삭제)")
    @DeleteMapping(value = "/{productId}")
    public void delete(
            @Parameter(name = "productId", description = "Product Id", example = "1", required = true)
            @PathVariable Long productId) {
        productUseCase.delete(productId);
    }
}
