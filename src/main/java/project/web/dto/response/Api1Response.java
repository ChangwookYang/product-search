package project.web.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Schema(description = "카테고리 별 최저가격 브랜드와 상품 가격, 총액")
@JsonInclude(NON_NULL)
public record Api1Response(
        List<ProductResponse> productList,
        Long totalPrice
) {
}
