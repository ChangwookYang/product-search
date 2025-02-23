package project.application.port.product.out;

import project.application.domain.Brand;
import project.application.domain.Product;
import project.application.dto.command.product.ProductPersistCommand;

import java.util.List;

public interface ProductPort {
    List<Product> findProductByCategoryIdAndBrandId(Long categoryId, Long brandId);

    Product findLowestPriceProductByCategory(Long categoryId);

    List<Product> findAllCategoryProductPriceByBrand(Long brandId);

    Product createOrUpdate(ProductPersistCommand persistCommand);

    void deleteById(Long productId);

    Product findById(Long productId);

    List<Product> findProductsByBrand(Brand brand);

    Product findCheapestBrandInCategory(Long categoryId);

    Product findPriciestBrandInCategory(Long categoryId);
}
