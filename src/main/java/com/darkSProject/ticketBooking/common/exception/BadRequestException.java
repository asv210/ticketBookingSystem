package com.darkSProject.ticketBooking.common.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException
        extends AppException {

    public BadRequestException(String message,ErrorCode errorCode) {
        super(
                message,
               HttpStatus.BAD_REQUEST,
               errorCode
        );
    }
}