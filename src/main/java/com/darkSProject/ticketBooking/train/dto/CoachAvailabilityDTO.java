package com.darkSProject.ticketBooking.train.dto;

import lombok.Builder;

@Builder
public record CoachAvailabilityDTO(

        String coach,

        Long availableSeats
) {
}
