package project.application.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Getter
public enum ProductErrorType implements BaseErrorType {
    PRODUCT_NOT_FOUND(NOT_FOUND, "상품 정보가 없습니다."),
    CHEAPEST_PRODUCT_ON_CATEGORY_NOT_FOUND(NOT_FOUND, "카테고리 최저 금액 상품이 없습니다."),
    DUPLICATED_PRODUCT_ON_CATEGORY_AND_BRAND(BAD_REQUEST, "동일 카테고리, 브랜드의 상품이 있습니다."),
    ;

    private final HttpStatus status;
    private final String message;

    ProductErrorType(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
