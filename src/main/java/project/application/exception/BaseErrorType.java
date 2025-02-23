package project.application.exception;

import org.springframework.http.HttpStatus;

public interface BaseErrorType {
    HttpStatus getStatus();
    String getMessage();
}
