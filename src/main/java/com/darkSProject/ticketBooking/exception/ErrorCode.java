package com.darkSProject.ticketBooking.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    USER_NOT_FOUND(
            HttpStatus.NOT_FOUND,
            "User not found"
    ),
    INVALID_CREDENTIALS(
            HttpStatus.UNAUTHORIZED,
            "Invalid credentials"
    ),

    TRAIN_NOT_FOUND(
            HttpStatus.NOT_FOUND,
            "Train not found"
    ),

    SEAT_UNAVAILABLE(
            HttpStatus.BAD_REQUEST,
            "Seat unavailable"
    );
    private final HttpStatus httpStatus;
    private final String message;

    ErrorCode(
            HttpStatus httpStatus,
            String message
    ){
        this.httpStatus=httpStatus;
        this.message=message;
    }

}
