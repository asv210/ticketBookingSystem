package com.darkSProject.ticketBooking.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    USER_NOT_FOUND,
    INVALID_CREDENTIALS,
    TRAIN_NOT_EXIST,
    TRAIN_EXISTS,
    SEATS_UNAVAILABLE,
    INVALID_ROUTE


}
