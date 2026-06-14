package com.darkSProject.ticketBooking.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

public class PaymentFailedException extends AppException {

    public PaymentFailedException(String message,ErrorCode errorCode) {
        super(message, (HttpStatus) HttpStatusCode.valueOf(422),errorCode);
    }
}
