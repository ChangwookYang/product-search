package project.web.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import project.application.exception.ApiException;
import project.web.dto.response.ErrorResponse;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        log.error(e.getClass().getName(), e);

        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ErrorResponse("서버에러가 발생하였습니다."));
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorResponse> handleApiException(ApiException e) {
        final HttpStatus httpStatus = e.getStatus();

        if (httpStatus.is5xxServerError()) {
            log.error(e.getClass().getName(), e);
        }

        return ResponseEntity.status(e.getStatus()).body(new ErrorResponse(e.getMessage()));
    }
}
