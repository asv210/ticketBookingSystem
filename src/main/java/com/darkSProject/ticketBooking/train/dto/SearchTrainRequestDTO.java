package com.darkSProject.ticketBooking.train.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public record SearchTrainRequestDTO(

        @NotBlank(message = "Source required")
        String source,

        @NotBlank(message = "Destination required")
        String destination,

        @NotNull(message = "Travel date required")
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        Date dateOfTravel

) {
}