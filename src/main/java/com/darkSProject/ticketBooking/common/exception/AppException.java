package com.darkSProject.ticketBooking.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class AppException extends RuntimeException {
    private final ErrorCode errorCode;
    private final HttpStatus httpStatus;
    public AppException(
            String message,

            HttpStatus status,

            ErrorCode errorCode
    ){

        super(message);
        this.httpStatus=status;
        this.errorCode=errorCode;
    }
}
