package com.darkSProject.ticketBooking.ticket.dto;

import com.darkSProject.ticketBooking.train.entity.CoachType;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.Date;

@Builder
public record BookTicketRequestDTO(

        @NotBlank(
                message = "Train No is required"
        )
        String trainNo,

        @NotBlank(
                message = "Source station is required"
        )
        String source,

        @NotBlank(
                message = "Destination station is required"
        )
        String destination,

        @NotNull(
                message = "Date of travel is required"
        )
        @FutureOrPresent(
                message = "Date of travel cannot be in the past"
        )
        Date dateOfTravel,

        @NotNull(
                message = "Number of seats is required"
        )
        @Min(
                value = 1,
                message = "At least 1 seat must be booked"
        )
        Integer numberOfSeats,
        @NotNull(
                message = "Coach type is required"
        )
        CoachType coachType

        ) {
}
