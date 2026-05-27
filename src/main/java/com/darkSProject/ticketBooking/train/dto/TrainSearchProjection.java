package com.darkSProject.ticketBooking.train.dto;


public record TrainSearchProjection(
        String trainId,
        String trainNo,
        String trainName,
        Long availableSeats
) {
}
