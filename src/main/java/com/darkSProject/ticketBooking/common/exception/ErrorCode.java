package com.darkSProject.ticketBooking.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    USER_NOT_FOUND,
    INVALID_CREDENTIALS,
    TRAIN_NOT_EXIST,
    TRAIN_EXISTS,
    SEATS_UNAVAILABLE,
    INVALID_ROUTE,
    USER_NOT_AUTHENTICATED,
    TICKET_NOT_FOUND,
    TICKET_ALREADY_CANCELLED,
    STATION_DOES_NOT_EXIST


}
