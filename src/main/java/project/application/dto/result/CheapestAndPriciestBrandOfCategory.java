package project.application.dto.result;

public record CheapestAndPriciestBrandOfCategory(
        CategoryResult category,
        ProductResult cheapestProduct,
        ProductResult priciestProduct
) {
}
