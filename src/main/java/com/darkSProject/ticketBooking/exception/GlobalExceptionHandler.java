package com.darkSProject.ticketBooking.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(AppException.class)
    public ResponseEntity<ApiErrorRespone> handleException(AppException ex){
        ErrorCode errorCode = ex.getErrorCode();

        ApiErrorRespone apiErrorRespone = new ApiErrorRespone(
                LocalDateTime.now(),
                errorCode.getHttpStatus().value(),
                errorCode.name(),
                errorCode.getMessage()

        );
        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(apiErrorRespone);
    }
}
