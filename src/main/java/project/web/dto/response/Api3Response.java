package project.web.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Schema(description = "최저가격 브랜드와 최고가격 브랜드")
@JsonInclude(NON_NULL)
public record Api3Response(
        String categoryName,
        ProductResponse cheapestProduct,
        ProductResponse priciestProduct
) {
}
