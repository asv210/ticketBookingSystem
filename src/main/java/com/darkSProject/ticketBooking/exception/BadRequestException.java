package com.darkSProject.ticketBooking.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException
        extends AppException {

    public BadRequestException() {
        super(
               ErrorCode.TRAIN_EXISTS
        );
    }
}