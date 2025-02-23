package project.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.application.dto.command.product.ProductCommand;
import project.application.dto.command.product.ProductPersistCommand;
import project.application.dto.result.ProductResult;
import project.application.exception.ApiException;
import project.application.mapper.ProductAppMapper;
import project.application.port.product.in.ProductUseCase;
import project.application.port.product.out.ProductPort;

import static project.application.exception.ProductErrorType.DUPLICATED_PRODUCT_ON_CATEGORY_AND_BRAND;

@Service
@RequiredArgsConstructor
public class ProductService implements ProductUseCase {

    private final ProductPort productPort;
    private final ProductAppMapper mapper;

    @Override
    @Transactional
    public ProductResult create(ProductCommand command) {
        var products = productPort.findProductByCategoryIdAndBrandId(command.categoryId(), command.brandId());
        if (!products.isEmpty()) {
            throw new ApiException(DUPLICATED_PRODUCT_ON_CATEGORY_AND_BRAND);
        }

        return mapper.toResult(productPort.createOrUpdate(mapper.toPersistCommand(command)));
    }

    @Override
    @Transactional
    public ProductResult update(Long productId, Long price) {
        var product = productPort.findById(productId);

        return mapper.toResult(productPort.createOrUpdate(
                new ProductPersistCommand(product.id(), product.category().id(), product.brand().id(), price)));
    }

    @Override
    @Transactional
    public void delete(Long productId) {
        productPort.deleteById(productId);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductResult findById(Long productId) {
        return mapper.toResult(productPort.findById(productId));
    }
}
