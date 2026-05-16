package com.darkSProject.ticketBooking.exception;

import java.time.LocalDateTime;


public record ApiErrorResponse(
        LocalDateTime localDateTime,
        int status,
        String error,
        String message

){}
