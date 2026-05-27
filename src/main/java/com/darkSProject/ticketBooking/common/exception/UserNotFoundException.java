package com.darkSProject.ticketBooking.common.exception;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends AppException{
    public UserNotFoundException(String messsage, ErrorCode errorCode){

        super(
                messsage,
                HttpStatus.UNAUTHORIZED,
                errorCode
        );
    }
}
