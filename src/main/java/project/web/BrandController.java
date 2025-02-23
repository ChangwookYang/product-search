package project.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import project.application.port.brand.in.BrandUseCase;
import project.web.dto.request.BrandRequest;
import project.web.dto.response.BrandResponse;
import project.web.mapper.BrandResponseMapper;
import project.web.mapper.BrandUpsertMapper;

@Tag(name = "브랜드 추가/업데이트/삭제 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/brand")
public class BrandController {

    private final BrandUseCase brandUseCase;
    private final BrandUpsertMapper upsertMapper;
    private final BrandResponseMapper responseMapper;

    @Operation(summary = "Brand 조회", description = "브랜드 조회")
    @GetMapping(value = "/{brandId}")
    public BrandResponse findById(
            @Parameter(name = "brandId", description = "Brand Id", example = "1", required = true)
            @PathVariable Long brandId) {
        return responseMapper.toResponse(brandUseCase.findById(brandId));
    }

    @Operation(summary = "Brand 생성", description = "브랜드명을 입력")
    @PostMapping
    public BrandResponse create(@RequestBody BrandRequest request) {
        return responseMapper.toResponse(
                brandUseCase.createOrUpdate(upsertMapper.toCommand(request)));
    }

    @Operation(summary = "Brand 업데이트", description = "변경할 Id값과 브랜드명을 입력")
    @PatchMapping(value = "/{brandId}")
    public BrandResponse update(
            @Parameter(name = "brandId", description = "Brand Id", example = "1", required = true)
            @PathVariable Long brandId,
            @RequestBody BrandRequest request) {
        return responseMapper.toResponse(
                brandUseCase.createOrUpdate(upsertMapper.toCommand(brandId, request)));
    }

    @Operation(summary = "Brand 삭제", description = "브랜드 삭제(브랜드의 상품도 삭제)")
    @DeleteMapping(value = "/{brandId}")
    public void delete(
            @Parameter(name = "brandId", description = "Brand Id", example = "1", required = true)
            @PathVariable Long brandId) {
        brandUseCase.delete(brandId);
    }
}
