package com.sewah.exceptions;
import com.sewah.exceptions.errors.FailedAuthenticationException;
import com.sewah.exceptions.errors.UserAlreadyExistsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    @ExceptionHandler(Exception.class)
    public final ResponseEntity<?> handleException(Exception ex) {
        log.error(String.format("Exception occurred: [ %s ] resulted in: [ %s ]", ex.getLocalizedMessage(), ex.getClass().getName()));
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorResponse.builder()
                        .error("Internal Server Error..")
                        .message(ex.getLocalizedMessage())
                        .build());
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public final ResponseEntity<?> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
        log.error(String.format("Exception occurred: [ %s ] resulted in: [ %s ]", ex.getLocalizedMessage(), ex.getClass().getName()));
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.builder()
                        .error("User Already Exist Exception")
                        .message(ex.getLocalizedMessage())
                        .build());
    }

    @ExceptionHandler(FailedAuthenticationException.class)
    public final ResponseEntity<?> handleAuthenticationFailedException(FailedAuthenticationException ex) {
        log.error(String.format("Exception occurred: [ %s ] resulted in: [ %s ]", ex.getLocalizedMessage(), ex.getClass().getName()));
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(ErrorResponse.builder()
                        .error("Authentication Failed..")
                        .message(ex.getLocalizedMessage())
                        .build());
    }

}
