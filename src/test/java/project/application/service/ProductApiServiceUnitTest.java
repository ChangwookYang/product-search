package project.application.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import project.application.exception.ApiException;
import project.application.mapper.BrandAppMapperImpl;
import project.application.mapper.CategoryAppMapperImpl;
import project.application.mapper.ProductAppMapperImpl;
import project.application.service.mock.BrandTestAdapter;
import project.application.service.mock.CategoryTestAdapter;
import project.application.service.mock.ProductTestAdapter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static project.application.exception.CategoryErrorType.CATEGORY_NOT_FOUND;

@DisplayName("Product API 유닛 테스트")
class ProductApiServiceUnitTest {

    private ProductApiService service;

    @BeforeEach
    void setUp() {
        service = new ProductApiService(
                new CategoryTestAdapter(),
                new BrandTestAdapter(),
                new ProductTestAdapter(),
                new ProductAppMapperImpl(),
                new BrandAppMapperImpl(),
                new CategoryAppMapperImpl()
        );
    }

    @Test
    void findCheapestProductInfoByCategory_success() {
        var result = service.findCheapestProductInfoByCategory();

        assertThat(result).isNotNull();
        assertThat(result.totalPrice()).isEqualTo(1000L + 20000L + 4000L);
    }

    @Test
    void findCheapestBrandInAllCategories_success() {
        var result = service.findCheapestBrandInAllCategories();

        assertThat(result).isNotNull();
        assertThat(result.brand().name()).isEqualTo("C");
        assertThat(result.totalPrice()).isEqualTo(26000L);
    }

    @Test
    void findCheapestAndPriciestBrandInCategory_success() {
        var categoryName = "상의";
        var result = service.findCheapestAndPriciestBrandInCategory(categoryName);

        assertThat(result).isNotNull();
        assertThat(result.category().name()).isEqualTo(categoryName);
        assertThat(result.cheapestProduct().id()).isEqualTo(1L);
        assertThat(result.priciestProduct().id()).isEqualTo(3L);
    }

    @Test
    void findCheapestAndPriciestBrandInCategory_없는_카테고리_예외처리() {
        var categoryName = "스니커즈";
        try {
            service.findCheapestAndPriciestBrandInCategory(categoryName);
        } catch (Exception e) {
            ApiException ex = (ApiException) e;
            assertThat(ex.getStatus()).isEqualTo(NOT_FOUND);
            assertThat(ex.getErrorMessage()).isEqualTo(CATEGORY_NOT_FOUND.getMessage());
        }
    }
}