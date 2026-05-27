package com.darkSProject.ticketBooking.ticket.dto;

import com.darkSProject.ticketBooking.train.entity.Coach;
import lombok.Builder;

@Builder
public record SeatInfoDTO(

        String coachName,

        Integer seatNumber
) {
}
