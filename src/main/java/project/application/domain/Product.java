package project.application.domain;

public record Product(
        Long id,
        Category category,
        Brand brand,
        Long price
) {
}
