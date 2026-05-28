package com.darkSProject.ticketBooking.ticket.dto;

import com.darkSProject.ticketBooking.ticket.entity.TicketStatus;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Builder
public record TicketResponseDTO(

        String ticketId,

        String trainNo,

        String trainName,

        List<SeatInfoDTO> seats,

        String source,

        String destination,

        Date dateOfTravel,

        TicketStatus status,

        LocalDateTime expireAt,

        Double totalFare
) {
}
