package com.darkSProject.ticketBooking.common.exception;

public class UnsupportedPaymentMethodException extends BadRequestException{
    public UnsupportedPaymentMethodException(String message,ErrorCode errorCode) {
        super(message, errorCode);
    }
}
