package com.darkSProject.ticketBooking.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record StationDTO(
        @NotBlank(message = "Station name cannot be empty")
        String stationName,
        @NotNull(message = "Station order is required")
        Integer stationOrder,

        Date arrivalTime,

        Date departureTime
){}
