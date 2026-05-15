package com.darkSProject.ticketBooking.exception;

import lombok.Getter;

import java.time.LocalDateTime;


public record ApiErrorRespone(
        LocalDateTime localDateTime,
        int status,
        String error,
        String message

){}
