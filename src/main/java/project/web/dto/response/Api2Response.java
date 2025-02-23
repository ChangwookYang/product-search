package project.web.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Schema(description = "단일 브랜드로 전체 카테고리 상품 최저 가격")
@JsonInclude(NON_NULL)
public record Api2Response(
        String brandName,
        List<ProductResponse> categoryList,
        Long totalPrice
) {
}
