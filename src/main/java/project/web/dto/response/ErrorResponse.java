package project.web.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Schema(description = "Error response")
public record ErrorResponse(
        @Schema(description = "message", example = "unauthorized", requiredMode = REQUIRED)
        String message
) {
}
