package com.darkSProject.ticketBooking.train.dto;

import com.darkSProject.ticketBooking.train.entity.CoachType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CoachDTO(
        @NotBlank(message = "Coach name is required")
        String coachName,
        @NotNull(message = "Total seats required")
        @Min(value = 1, message = "Seats must be greater than 0")
        Integer totalSeats,
        @NotBlank(message = "Coach type is required")
        CoachType coachType,
        @NotNull(message = "Fare per station required")
        @Min(value = 1, message = "Fare must be greater than 0")
        Double farePerStation
) {

}
