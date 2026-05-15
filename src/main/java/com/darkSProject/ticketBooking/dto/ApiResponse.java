package com.darkSProject.ticketBooking.dto;

import lombok.Builder;


@Builder
public record ApiResponse<T>(
        String message,
        boolean success,
        T data
) {

}
