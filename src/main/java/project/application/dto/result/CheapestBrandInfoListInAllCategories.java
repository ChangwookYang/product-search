package project.application.dto.result;

import java.util.List;

public record CheapestBrandInfoListInAllCategories(
        BrandResult brand,
        List<ProductResult> productList,
        Long totalPrice
) {

}
