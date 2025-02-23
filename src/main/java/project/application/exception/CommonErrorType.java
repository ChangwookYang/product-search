package project.application.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum CommonErrorType implements BaseErrorType {
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 에러 발생"),
    ;

    private final HttpStatus status;
    private final String message;

    CommonErrorType(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
