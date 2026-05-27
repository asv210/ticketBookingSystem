package com.darkSProject.ticketBooking.train.dto;

import lombok.Builder;

import java.util.Date;
import java.util.List;

@Builder
public record SeatAvailabilityResponseDTO(

        String trainNo,

        String trainName,

        String source,

        String destination,

        Date dateOfTravel,

        Long totalAvailableSeats,

        List<CoachAvailabilityDTO> coaches
) {
}
