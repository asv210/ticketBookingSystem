package com.darkSProject.ticketBooking.dto;

import lombok.AllArgsConstructor;


public record TrainSearchProjection(
        String trainId,
        String trainNo,
        String trainName,
        Long availableSeats
) {
}
