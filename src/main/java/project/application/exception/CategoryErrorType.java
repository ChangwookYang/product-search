package project.application.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Getter
public enum CategoryErrorType implements BaseErrorType {
    CATEGORY_NOT_FOUND(NOT_FOUND, "카테고리 정보가 없습니다."),
    ;

    private final HttpStatus status;
    private final String message;

    CategoryErrorType(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
