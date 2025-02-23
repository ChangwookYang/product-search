package project.application.port.product.in;

import project.application.dto.command.product.ProductCommand;
import project.application.dto.result.ProductResult;

public interface ProductUseCase {

    ProductResult findById(Long productId);

    ProductResult create(ProductCommand command);

    ProductResult update(Long productId, Long price);

    void delete(Long productId);
}
