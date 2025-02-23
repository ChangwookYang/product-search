package project.application.dto.result;

public record ProductResult(
        Long id,
        BrandResult brand,
        CategoryResult category,
        Long price
) {
}
