package com.darkSProject.ticketBooking.controller;

import com.darkSProject.ticketBooking.dto.ApiResponse;
import com.darkSProject.ticketBooking.dto.BookTicketRequestDTO;
import com.darkSProject.ticketBooking.dto.TicketResponseDTO;
import com.darkSProject.ticketBooking.services.TicketService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tickets")
@RequiredArgsConstructor
public class TicketController {
    private final TicketService ticketService;

    @PostMapping("/book")
    public ResponseEntity<ApiResponse<TicketResponseDTO>>
    bookTicket(

            @Valid
            @RequestBody
            BookTicketRequestDTO request
    ) {

        ApiResponse<TicketResponseDTO> response =

                ticketService.bookTicket(
                        request
                );

        return ResponseEntity.ok(response);
    }
}
