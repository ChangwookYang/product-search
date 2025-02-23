package project.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "상품/브랜드/카테고리 API ",
                version = "1.0.0",
                description = "상품/브랜드/카테고리의 특정 조건에 따라 조회 & 상품 및 브랜드 추가/수정/삭제 API 문서"
        )
)
public class SwaggerConfig {
}