package project.application.dto.command.product;

public record ProductPersistCommand(
        Long id,
        Long categoryId,
        Long brandId,
        Long price
) {
}
