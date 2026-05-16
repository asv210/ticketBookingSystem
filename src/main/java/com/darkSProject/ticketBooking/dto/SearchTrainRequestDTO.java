package com.darkSProject.ticketBooking.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record SearchTrainRequestDTO(

        @NotBlank(message = "Source required")
        String source,

        @NotBlank(message = "Destination required")
        String destination,

        @NotNull(message = "Travel date required")
        Date dateOfTravel

) {
}