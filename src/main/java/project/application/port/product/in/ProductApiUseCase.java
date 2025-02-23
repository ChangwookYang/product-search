package project.application.port.product.in;

import project.application.dto.result.CheapestAndPriciestBrandOfCategory;
import project.application.dto.result.CheapestBrandInfoListInAllCategories;
import project.application.dto.result.CheapestProductInfoListInCategory;

public interface ProductApiUseCase {
    CheapestProductInfoListInCategory findCheapestProductInfoByCategory();
    CheapestBrandInfoListInAllCategories findCheapestBrandInAllCategories();
    CheapestAndPriciestBrandOfCategory findCheapestAndPriciestBrandInCategory(String categoryName);
}
