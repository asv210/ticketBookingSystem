package com.darkSProject.ticketBooking.ticket.service;

import com.darkSProject.ticketBooking.common.dto.ApiResponse;
import com.darkSProject.ticketBooking.common.dto.PaginationResponseDTO;
import com.darkSProject.ticketBooking.ticket.dto.BookTicketRequestDTO;
import com.darkSProject.ticketBooking.ticket.dto.TicketResponseDTO;
import com.darkSProject.ticketBooking.ticket.entity.TicketStatus;

public interface TicketService {
    public ApiResponse<TicketResponseDTO> bookTicket(
            BookTicketRequestDTO request
    );

    public ApiResponse<String> cancelTicket(
            String ticketId
    );

    public ApiResponse<PaginationResponseDTO<TicketResponseDTO>> myBookings(
            int page,
            int size,
            TicketStatus status);

    public  ApiResponse<TicketResponseDTO>
}
