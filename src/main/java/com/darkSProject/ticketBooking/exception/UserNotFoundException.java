package com.darkSProject.ticketBooking.exception;

public class UserNotFoundException extends AppException{
    public UserNotFoundException(){
        super(ErrorCode.USER_NOT_FOUND);
    }
}
