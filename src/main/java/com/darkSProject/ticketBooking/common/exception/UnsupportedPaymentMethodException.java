package com.darkSProject.ticketBooking.common.exception;

import org.springframework.http.HttpStatus;

public class UnsupportedPaymentMethodException extends AppException {
    public UnsupportedPaymentMethodException(String message, ErrorCode errorCode){
        super(
                message,
                HttpStatus.,
                errorCode
        );
    }
}
