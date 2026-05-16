package com.darkSProject.ticketBooking.dto;

import lombok.Builder;

import java.util.Date;
import java.util.List;

@Builder
public record TicketResponseDTO(

        String ticketId,

        String trainNumber,

        String trainName,

        List<SeatInfoDTO> seats,

        String source,

        String destination,

        Date dateOfTravel
) {
}
