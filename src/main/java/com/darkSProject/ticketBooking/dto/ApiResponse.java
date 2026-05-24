package com.darkSProject.ticketBooking.dto;

import lombok.Builder;


@Builder

public record ApiResponse<T>(
        String message,
        boolean success,
        T data
) {

    public static <T> ApiResponse<T> success(

            String message,

            T data
    ) {

        return ApiResponse.<T>builder()

                .message(message)

                .success(true)

                .data(data)

                .build();
    }

    public static <T> ApiResponse<T> failure(

            String message,

            T data
    ) {

        return ApiResponse.<T>builder()

                .message(message)

                .success(false)

                .data(data)

                .build();
    }
}
