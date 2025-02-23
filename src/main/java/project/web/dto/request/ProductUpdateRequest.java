package project.web.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Schema(description = "상품 업데이트 request")
public record ProductUpdateRequest(
        @Schema(description = "가격", example = "3000", requiredMode = REQUIRED)
        @NotNull
        Long price
) {

}
