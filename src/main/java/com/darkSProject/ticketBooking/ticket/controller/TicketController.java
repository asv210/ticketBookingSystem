package com.darkSProject.ticketBooking.ticket.controller;

import com.darkSProject.ticketBooking.common.dto.ApiResponse;
import com.darkSProject.ticketBooking.common.dto.PaginationResponseDTO;
import com.darkSProject.ticketBooking.ticket.dto.BookTicketRequestDTO;
import com.darkSProject.ticketBooking.ticket.dto.TicketResponseDTO;
import com.darkSProject.ticketBooking.ticket.entity.TicketStatus;
import com.darkSProject.ticketBooking.ticket.service.TicketService;
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

    @PatchMapping("/cancel")
    public ResponseEntity<ApiResponse<String>>cancelTicket(@RequestParam String ticketId){
       return ResponseEntity
               .ok(
                       ticketService.cancelTicket(ticketId)
               );
    }

    @GetMapping("/my-bookings")
    public ResponseEntity<ApiResponse<PaginationResponseDTO<TicketResponseDTO>>> getMybooking(
            @RequestParam(
                    defaultValue = "0"
            )
            int page,
            @RequestParam(
                    defaultValue = "10"
            )
            int size,
            @RequestParam(
                    required = false
            )
            TicketStatus status
    ){
        return ResponseEntity.ok(
                ticketService.myBookings(
                        page,
                        size,
                        status
                )
        );

    }



}
