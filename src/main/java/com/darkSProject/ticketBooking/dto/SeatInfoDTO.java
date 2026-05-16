package com.darkSProject.ticketBooking.dto;

import lombok.Builder;

@Builder
public record SeatInfoDTO(

        String coach,

        Integer seatNumber
) {
}
