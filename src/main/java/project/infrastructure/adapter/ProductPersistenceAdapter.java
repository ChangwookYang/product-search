package project.infrastructure.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import project.application.domain.Brand;
import project.application.domain.Product;
import project.application.dto.command.product.ProductPersistCommand;
import project.application.exception.ApiException;
import project.application.port.product.out.ProductPort;
import project.infrastructure.entity.BrandEntity;
import project.infrastructure.entity.CategoryEntity;
import project.infrastructure.entity.ProductEntity;
import project.infrastructure.mapper.BrandInfraMapper;
import project.infrastructure.mapper.ProductInfraMapper;
import project.infrastructure.repository.BrandRepository;
import project.infrastructure.repository.CategoryRepository;
import project.infrastructure.repository.ProductRepository;

import java.util.List;

import static project.application.exception.BrandErrorType.BRAND_NOT_FOUND;
import static project.application.exception.CategoryErrorType.CATEGORY_NOT_FOUND;
import static project.application.exception.ProductErrorType.CHEAPEST_PRODUCT_ON_CATEGORY_NOT_FOUND;
import static project.application.exception.ProductErrorType.PRODUCT_NOT_FOUND;

@Repository
@RequiredArgsConstructor
public class ProductPersistenceAdapter implements ProductPort {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;

    private final ProductInfraMapper productMapper;
    private final BrandInfraMapper brandMapper;

    @Override
    public List<Product> findProductByCategoryIdAndBrandId(Long categoryId, Long brandId) {
        return productMapper.toDomains(productRepository.findProducts(categoryId, brandId));
    }

    @Override
    public Product findLowestPriceProductByCategory(Long categoryId) {
        return productMapper.toDomain(
                productRepository.findLowestPriceProductByCategory(categoryId)
                        .orElseThrow(() -> new ApiException(CHEAPEST_PRODUCT_ON_CATEGORY_NOT_FOUND))
        );
    }

    @Override
    public Product createOrUpdate(ProductPersistCommand command) {
        CategoryEntity categoryEntity = categoryRepository.findById(command.categoryId())
                .orElseThrow(() -> new ApiException(CATEGORY_NOT_FOUND));

        BrandEntity brandEntity = brandRepository.findById(command.brandId())
                .orElseThrow(() -> new ApiException(BRAND_NOT_FOUND));

        return productMapper.toDomain(
                productRepository.save(new ProductEntity(command.id(), categoryEntity, brandEntity, command.price())));
    }

    @Override
    public void deleteById(Long productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public Product findById(Long productId) {
        return productMapper.toDomain(productRepository.findById(productId).orElseThrow(() -> new ApiException(PRODUCT_NOT_FOUND)));
    }

    @Override
    public List<Product> findProductsByBrand(Brand brand) {
        return productMapper.toDomains(productRepository.findByBrand(brandMapper.toEntity(brand)));
    }

    @Override
    public List<Product> findAllCategoryProductPriceByBrand(Long brandId) {
        return productMapper.toDomains(productRepository.findAllCategoryProductPriceByBrand(brandId));
    }

    @Override
    public Product findCheapestBrandInCategory(Long categoryId) {
        ProductEntity productEntity = productRepository.findCheapestBrandInCategory(categoryId)
                .orElseThrow(() -> new ApiException(PRODUCT_NOT_FOUND));

        return productMapper.toDomain(productEntity);
    }

    @Override
    public Product findPriciestBrandInCategory(Long categoryId) {
        ProductEntity productEntity = productRepository.findPriciestBrandInCategory(categoryId)
                .orElseThrow(() -> new ApiException(PRODUCT_NOT_FOUND));

        return productMapper.toDomain(productEntity);
    }
}
