package project.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;
import project.web.dto.request.ProductCreateRequest;
import project.web.dto.response.ErrorResponse;
import project.web.dto.response.ProductResponse;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static project.application.exception.ProductErrorType.DUPLICATED_PRODUCT_ON_CATEGORY_AND_BRAND;

@DisplayName("Product API 통합 테스트")
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Product 생성")
    void create() throws Exception {
        ProductCreateRequest createRequest = new ProductCreateRequest(1L, 1L, 1000L);

        MvcResult result = mockMvc.perform(post("/product")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createRequest)))
                .andExpect(status().is4xxClientError())
                .andReturn();

        ErrorResponse response = objectMapper.readValue(result.getResponse().getContentAsString(), ErrorResponse.class);
        assertThat(response.message()).isEqualTo(DUPLICATED_PRODUCT_ON_CATEGORY_AND_BRAND.getMessage());
    }

    @Test
    @DisplayName("Product 수정")
    void update() throws Exception {
        Long price = 2000L;
        ProductCreateRequest request = new ProductCreateRequest(1L, 1L, price);

        MvcResult result = mockMvc.perform(patch("/product/{productId}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andReturn();

        ProductResponse response = objectMapper.readValue(result.getResponse().getContentAsString(), ProductResponse.class);

        assertThat(response.id()).isEqualTo(1L);
        assertThat(response.price()).isEqualTo(price);
    }

    @Test
    @DisplayName("Product 삭제")
    void deleteProduct() throws Exception {
        mockMvc.perform(delete("/product/{productId}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}