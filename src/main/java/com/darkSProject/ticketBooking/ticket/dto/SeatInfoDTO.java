package com.darkSProject.ticketBooking.ticket.dto;

import lombok.Builder;

@Builder
public record SeatInfoDTO(

        String coach,

        Integer seatNumber
) {
}
