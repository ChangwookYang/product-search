package project.infrastructure.repository;

import project.infrastructure.entity.ProductEntity;

import java.util.List;
import java.util.Optional;

public interface ProductRepositoryCustom {
    List<ProductEntity> findProducts(Long categoryId, Long brandId);

    Optional<ProductEntity> findLowestPriceProductByCategory(Long categoryId);

    List<ProductEntity> findAllCategoryProductPriceByBrand(Long brandId);

    Optional<ProductEntity> findCheapestBrandInCategory(Long categoryId);

    Optional<ProductEntity> findPriciestBrandInCategory(Long categoryId);
}