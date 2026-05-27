package com.darkSProject.ticketBooking.train.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CoachDTO(
        @NotBlank(message = "Coach name is required")
        String coachName,
        @NotNull(message = "Total seats required")
        @Min(value = 1, message = "Seats must be greater than 0")
        Integer totalSeats
) {

}
