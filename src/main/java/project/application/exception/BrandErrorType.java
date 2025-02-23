package project.application.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Getter
public enum BrandErrorType implements BaseErrorType {
    BRAND_NOT_FOUND(NOT_FOUND, "브랜드가 없습니다."),
    BRAND_NAME_DUPLICATED(BAD_REQUEST, "이미 등록된 브랜드명입니다."),
    ;

    private final HttpStatus status;
    private final String message;

    BrandErrorType(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
