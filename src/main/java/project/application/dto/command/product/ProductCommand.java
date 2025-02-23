package project.application.dto.command.product;

public record ProductCommand(
        Long id,
        Long categoryId,
        Long brandId,
        Long price
) {
}
