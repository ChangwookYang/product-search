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
import project.web.dto.request.BrandRequest;
import project.web.dto.response.BrandResponse;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Brand API 통합 테스트")
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class BrandControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("브랜드 생성")
    void createBrand() throws Exception {
        String brandName = "테스트";
        BrandRequest createRequest = new BrandRequest(brandName);

        MvcResult result = mockMvc.perform(post("/brand")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createRequest)))
                .andExpect(status().isOk())
                .andReturn();

        BrandResponse response = objectMapper.readValue(result.getResponse().getContentAsString(), BrandResponse.class);

        assertThat(response.id()).isNotNull();
        assertThat(response.name()).isEqualTo(brandName);
    }

    @Test
    @DisplayName("브랜드 수정")
    void updateBrand() throws Exception {
        String updatedBrandName = "업데이트브랜드";
        BrandRequest updateRequest = new BrandRequest(updatedBrandName);

        MvcResult result = mockMvc.perform(patch("/brand/{brandId}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateRequest)))
                .andExpect(status().isOk())
                .andReturn();

        BrandResponse response = objectMapper.readValue(result.getResponse().getContentAsString(), BrandResponse.class);

        assertThat(response.id()).isEqualTo(1L);
        assertThat(response.name()).isEqualTo(updatedBrandName);
    }

    @Test
    @DisplayName("브랜드 삭제")
    void deleteBrand() throws Exception {
        mockMvc.perform(delete("/brand/{brandId}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}