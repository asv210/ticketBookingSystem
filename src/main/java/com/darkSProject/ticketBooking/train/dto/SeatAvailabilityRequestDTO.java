package com.darkSProject.ticketBooking.train.dto;

import lombok.Builder;

import java.util.Date;

@Builder
public record SeatAvailabilityRequestDTO(

        String trainNo,

        String source,

        String destination,

        Date dateOfTravel
) {
}
