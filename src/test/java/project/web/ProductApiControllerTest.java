package project.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;
import project.web.dto.response.Api1Response;
import project.web.dto.response.Api2Response;
import project.web.dto.response.Api3Response;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("API 통합 테스트")
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class ProductApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("카테고리 별 최저 가격 조회 API 테스트")
    void findCheapestProductInfoByCategory() throws Exception {
        MvcResult result = mockMvc.perform(get("/products/cheapest-by-category")
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        Api1Response response = objectMapper.readValue(result.getResponse().getContentAsString(), Api1Response.class);

        assertThat(response.totalPrice()).isEqualTo(34100L);

        assertThat(response.productList().size()).isEqualTo(8);
        assertThat(response.productList().get(0).categoryName()).isEqualTo("상의");
        assertThat(response.productList().get(0).brandName()).isEqualTo("C");
        assertThat(response.productList().get(0).price()).isEqualTo(10000L);
        assertThat(response.productList().get(1).categoryName()).isEqualTo("아우터");
        assertThat(response.productList().get(1).brandName()).isEqualTo("E");
        assertThat(response.productList().get(1).price()).isEqualTo(5000L);
        assertThat(response.productList().get(2).categoryName()).isEqualTo("바지");
        assertThat(response.productList().get(2).brandName()).isEqualTo("D");
        assertThat(response.productList().get(2).price()).isEqualTo(3000L);
        assertThat(response.productList().get(3).categoryName()).isEqualTo("스니커즈");
        assertThat(response.productList().get(3).brandName()).isIn("A", "G");
        assertThat(response.productList().get(3).price()).isEqualTo(9000L);
        assertThat(response.productList().get(4).categoryName()).isEqualTo("가방");
        assertThat(response.productList().get(4).brandName()).isEqualTo("A");
        assertThat(response.productList().get(4).price()).isEqualTo(2000L);
        assertThat(response.productList().get(5).categoryName()).isEqualTo("모자");
        assertThat(response.productList().get(5).brandName()).isEqualTo("D");
        assertThat(response.productList().get(5).price()).isEqualTo(1500L);
        assertThat(response.productList().get(6).categoryName()).isEqualTo("양말");
        assertThat(response.productList().get(6).brandName()).isEqualTo("I");
        assertThat(response.productList().get(6).price()).isEqualTo(1700L);
        assertThat(response.productList().get(7).categoryName()).isEqualTo("액세서리");
        assertThat(response.productList().get(7).brandName()).isEqualTo("F");
        assertThat(response.productList().get(7).price()).isEqualTo(1900L);
    }

    @Test
    @DisplayName("단일 브랜드로 전체 카테고리 상품 최저 가격 조회 API 테스트")
    void findCheapestBrandInAllCategories() throws Exception {
        MvcResult result = mockMvc.perform(get("/products/categories/cheapest-brand")
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        Api2Response response = objectMapper.readValue(result.getResponse().getContentAsString(), Api2Response.class);

        assertThat(response.brandName()).isEqualTo("D");
        assertThat(response.totalPrice()).isEqualTo(36100L);

        assertThat(response.categoryList().size()).isEqualTo(8);
        assertThat(response.categoryList().get(0).categoryName()).isEqualTo("상의");
        assertThat(response.categoryList().get(0).price()).isEqualTo(10100L);
        assertThat(response.categoryList().get(1).categoryName()).isEqualTo("아우터");
        assertThat(response.categoryList().get(1).price()).isEqualTo(5100L);
        assertThat(response.categoryList().get(2).categoryName()).isEqualTo("바지");
        assertThat(response.categoryList().get(2).price()).isEqualTo(3000L);
        assertThat(response.categoryList().get(3).categoryName()).isEqualTo("스니커즈");
        assertThat(response.categoryList().get(3).price()).isEqualTo(9500L);
        assertThat(response.categoryList().get(4).categoryName()).isEqualTo("가방");
        assertThat(response.categoryList().get(4).price()).isEqualTo(2500L);
        assertThat(response.categoryList().get(5).categoryName()).isEqualTo("모자");
        assertThat(response.categoryList().get(5).price()).isEqualTo(1500L);
        assertThat(response.categoryList().get(6).categoryName()).isEqualTo("양말");
        assertThat(response.categoryList().get(6).price()).isEqualTo(2400L);
        assertThat(response.categoryList().get(7).categoryName()).isEqualTo("액세서리");
        assertThat(response.categoryList().get(7).price()).isEqualTo(2000L);
    }

    @Test
    @DisplayName("특정 카테고리에서 최저가/최고가 브랜드 조회 API 테스트")
    void findCheapestAndPriciestBrandInCategory() throws Exception {
        String categoryName = "상의";

        MvcResult result = mockMvc.perform(
                        get("/products/cheapest-and-priciest")
                                .param("categoryName", categoryName)
                                .contentType(APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andReturn();

        Api3Response response = objectMapper.readValue(result.getResponse().getContentAsString(), Api3Response.class);

        assertThat(response.categoryName()).isEqualTo(categoryName);

        assertThat(response.cheapestProduct().brandName()).isEqualTo("C");
        assertThat(response.cheapestProduct().price()).isEqualTo(10000L);

        assertThat(response.priciestProduct().brandName()).isEqualTo("I");
        assertThat(response.priciestProduct().price()).isEqualTo(11400L);
    }
}