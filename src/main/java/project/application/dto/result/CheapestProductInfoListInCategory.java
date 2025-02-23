package project.application.dto.result;

import java.util.List;

public record CheapestProductInfoListInCategory(
        List<ProductResult> productList,
        Long totalPrice
) {
}
