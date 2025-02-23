package project.application.exception;

import org.springframework.http.HttpStatus;

public class ApiException extends RuntimeException {
    private final BaseErrorType errorType;

    public ApiException(BaseErrorType errorType) {
        super(errorType.getMessage());
        this.errorType = errorType;
    }

    public HttpStatus getStatus() {
        return errorType.getStatus();
    }

    public String getErrorMessage() {
        return errorType.getMessage();
    }
}
