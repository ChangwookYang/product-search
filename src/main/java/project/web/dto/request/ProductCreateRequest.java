package project.web.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Schema(description = "상품 생성 request")
public record ProductCreateRequest(
        @Schema(description = "카테고리 ID", example = "1", requiredMode = REQUIRED)
        @NotNull
        Long categoryId,
        @Schema(description = "브랜드 ID", example = "1", requiredMode = REQUIRED)
        @NotNull
        Long brandId,
        @Schema(description = "가격", example = "3000", requiredMode = REQUIRED)
        @NotNull
        Long price
) {

}
