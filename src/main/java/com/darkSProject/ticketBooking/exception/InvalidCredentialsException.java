package com.darkSProject.ticketBooking.exception;

public class InvalidCredentialsException extends AppException{
    public InvalidCredentialsException(){
        super(ErrorCode.INVALID_CREDENTIALS);
    }
}
