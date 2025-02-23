package project.application.service.mock;

import project.application.domain.Brand;
import project.application.domain.Category;
import project.application.domain.Product;
import project.application.dto.command.product.ProductPersistCommand;
import project.application.exception.ApiException;
import project.application.port.product.out.ProductPort;

import java.util.Comparator;
import java.util.List;

import static project.application.exception.BrandErrorType.BRAND_NOT_FOUND;
import static project.application.exception.CategoryErrorType.CATEGORY_NOT_FOUND;
import static project.application.exception.ProductErrorType.PRODUCT_NOT_FOUND;

public class ProductTestAdapter implements ProductPort {

    private final List<Product> productList = TestDataFactory.getProducts();
    private final List<Category> categoryList = TestDataFactory.getCategories();
    private final List<Brand> brandList = TestDataFactory.getBrands();

    @Override
    public List<Product> findProductByCategoryIdAndBrandId(Long categoryId, Long brandId) {
        return productList.stream()
                .filter(product -> product.category().id().equals(categoryId) && product.brand().id().equals(brandId))
                .toList();
    }

    @Override
    public Product findLowestPriceProductByCategory(Long categoryId) {
        return productList.stream()
                .filter(product -> product.category().id().equals(categoryId))
                .min(Comparator.comparing(Product::price))
                .orElseThrow(() -> new ApiException(PRODUCT_NOT_FOUND));
    }

    @Override
    public List<Product> findAllCategoryProductPriceByBrand(Long brandId) {
        return productList.stream()
                .filter(product -> product.brand().id().equals(brandId))
                .toList();
    }

    @Override
    public Product createOrUpdate(ProductPersistCommand command) {
        if (command.id() == null) {
            return new Product(
                    productList.size() + 1L,
                    categoryList.stream().filter(category -> category.id().equals(command.categoryId()))
                            .findFirst().orElseThrow(() -> new ApiException(CATEGORY_NOT_FOUND)),
                    brandList.stream().filter(brand -> brand.id().equals(command.brandId()))
                            .findFirst().orElseThrow(() -> new ApiException(BRAND_NOT_FOUND)),
                    command.price()
            );
        } else {
            return new Product(
                    command.id(),
                    categoryList.stream().filter(category -> category.id().equals(command.categoryId()))
                            .findFirst().orElseThrow(() -> new ApiException(CATEGORY_NOT_FOUND)),
                    brandList.stream().filter(brand -> brand.id().equals(command.brandId()))
                            .findFirst().orElseThrow(() -> new ApiException(BRAND_NOT_FOUND)),
                    command.price()
            );
        }
    }

    @Override
    public void deleteById(Long productId) {
        productList.removeIf(product -> product.id().equals(productId));
    }

    @Override
    public Product findById(Long productId) {
        return productList.stream()
                .filter(product -> product.id().equals(productId))
                .findFirst()
                .orElseThrow(() -> new ApiException(PRODUCT_NOT_FOUND));
    }

    @Override
    public List<Product> findProductsByBrand(Brand brand) {
        return productList.stream()
                .filter(product -> product.brand().equals(brand))
                .toList();
    }

    @Override
    public Product findCheapestBrandInCategory(Long categoryId) {
        return productList.stream()
                .filter(product -> product.category().id().equals(categoryId))
                .min(Comparator.comparingLong(Product::price))
                .orElse(null);
    }

    @Override
    public Product findPriciestBrandInCategory(Long categoryId) {
        return productList.stream()
                .filter(product -> product.category().id().equals(categoryId))
                .max(Comparator.comparingLong(Product::price))
                .orElse(null);
    }
}
