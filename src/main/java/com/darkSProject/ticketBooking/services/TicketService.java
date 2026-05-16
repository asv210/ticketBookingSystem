package com.darkSProject.ticketBooking.services;

import com.darkSProject.ticketBooking.dto.ApiResponse;
import com.darkSProject.ticketBooking.dto.BookTicketRequestDTO;
import com.darkSProject.ticketBooking.dto.TicketResponseDTO;

public interface TicketService {
    public ApiResponse<TicketResponseDTO> bookTicket(
        BookTicketRequestDTO request
    );
}
