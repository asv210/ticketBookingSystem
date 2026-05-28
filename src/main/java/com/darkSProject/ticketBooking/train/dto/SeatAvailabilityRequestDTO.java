package com.darkSProject.ticketBooking.train.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Builder
public record SeatAvailabilityRequestDTO(

        String trainNo,
        @NotBlank(message = "Source required")
        String source,

        @NotBlank(message = "Destination required")
        String destination,

        @NotNull(message = "Travel date required")
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        Date dateOfTravel
) {
}
