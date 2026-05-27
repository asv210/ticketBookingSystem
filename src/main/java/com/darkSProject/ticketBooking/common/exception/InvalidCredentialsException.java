package com.darkSProject.ticketBooking.common.exception;

import org.springframework.http.HttpStatus;

public class InvalidCredentialsException extends AppException{
    public InvalidCredentialsException(String messsage, ErrorCode errorCode){

        super(
                messsage,
                HttpStatus.UNAUTHORIZED,
                errorCode
        );
    }
}
