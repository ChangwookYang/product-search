package project.application.service.mock;

import project.application.domain.Brand;
import project.application.domain.Category;
import project.application.domain.Product;

import java.util.Arrays;
import java.util.List;

public class TestDataFactory {
    public static List<Brand> getBrands() {
        return Arrays.asList(
                new Brand(1L, "A"),
                new Brand(2L, "B"),
                new Brand(3L, "C")
        );
    }

    public static List<Category> getCategories() {
        return Arrays.asList(
                new Category(1L, "상의"),
                new Category(2L, "아우터"),
                new Category(3L, "바지")
        );
    }

    public static List<Product> getProducts() {
        List<Category> categories = getCategories();
        List<Brand> brands = getBrands();

        return Arrays.asList(
                new Product(1L, categories.get(0), brands.get(0), 1000L), // 상의 - A
                new Product(2L, categories.get(0), brands.get(1), 1500L), // 상의 - B
                new Product(3L, categories.get(0), brands.get(2), 2000L), // 상의 - C
                new Product(4L, categories.get(1), brands.get(0), 30000L), // 아우터 - A
                new Product(5L, categories.get(1), brands.get(1), 25000L), // 아우터 - B
                new Product(6L, categories.get(1), brands.get(2), 20000L), // 아우터 - C
                new Product(7L, categories.get(2), brands.get(0), 5000L), // 바지 - A
                new Product(8L, categories.get(2), brands.get(1), 5500L), // 바지 - B
                new Product(9L, categories.get(2), brands.get(2), 4000L)  // 바지 - C
        );
    }
}
