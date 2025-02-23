package project.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.application.domain.Brand;
import project.application.domain.Category;
import project.application.domain.Product;
import project.application.dto.result.CheapestAndPriciestBrandOfCategory;
import project.application.dto.result.CheapestBrandInfoListInAllCategories;
import project.application.dto.result.CheapestProductInfoListInCategory;
import project.application.mapper.BrandAppMapper;
import project.application.mapper.CategoryAppMapper;
import project.application.mapper.ProductAppMapper;
import project.application.port.brand.out.BrandPort;
import project.application.port.category.out.CategoryPort;
import project.application.port.product.in.ProductApiUseCase;
import project.application.port.product.out.ProductPort;

import static java.lang.Long.MAX_VALUE;

@Service
@RequiredArgsConstructor
public class ProductApiService implements ProductApiUseCase {

    private final CategoryPort categoryPort;
    private final BrandPort brandPort;
    private final ProductPort productPort;

    private final ProductAppMapper productMapper;
    private final BrandAppMapper brandMapper;
    private final CategoryAppMapper categoryMapper;

    @Override
    @Transactional(readOnly = true)
    public CheapestProductInfoListInCategory findCheapestProductInfoByCategory() {
        var categoryList = categoryPort.findAll();

        var productList = categoryList.stream()
                .map(category -> productPort.findLowestPriceProductByCategory(category.id()))
                .toList();

        var totalPrice = productList.stream()
                .mapToLong(Product::price)
                .sum();

        return new CheapestProductInfoListInCategory(productMapper.toResults(productList), totalPrice);
    }

    @Override
    @Transactional(readOnly = true)
    public CheapestBrandInfoListInAllCategories findCheapestBrandInAllCategories() {
        var brandList = brandPort.findAll();
        var categoryList = categoryPort.findAll();

        Brand cheapestBrand = null;
        Long cheapestPrice = MAX_VALUE;
        for (var brand : brandList) {
            var productList = productPort.findAllCategoryProductPriceByBrand(brand.id());
            if (productList.size() != categoryList.size()) {
                // 브랜드가 모든 카테고리의 상품을 가지지 않을 경우 continue;
                continue;
            }

            var totalPrice = productList.stream().mapToLong(Product::price).sum();
            if (totalPrice < cheapestPrice) {
                cheapestBrand = brand;
                cheapestPrice = totalPrice;
            }
        }

        return new CheapestBrandInfoListInAllCategories(
                brandMapper.toResult(cheapestBrand),
                productMapper.toResults(productPort.findProductsByBrand(cheapestBrand)),
                cheapestPrice
        );
    }

    @Override
    @Transactional(readOnly = true)
    public CheapestAndPriciestBrandOfCategory findCheapestAndPriciestBrandInCategory(String categoryName) {

        Category category = categoryPort.findByName(categoryName);

        Product cheapestProduct = productPort.findCheapestBrandInCategory(category.id());
        Product pricestProduct = productPort.findPriciestBrandInCategory(category.id());

        return new CheapestAndPriciestBrandOfCategory(
                categoryMapper.toResult(category),
                productMapper.toResult(cheapestProduct),
                productMapper.toResult(pricestProduct)
        );
    }
}
