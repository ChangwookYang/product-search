package project.web.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Schema(description = "브랜드 생성/업데이트 request")
public record BrandRequest(
        @Schema(description = "브랜드명", example = "AA", requiredMode = REQUIRED)
        @NotNull
        String name
) {

}
