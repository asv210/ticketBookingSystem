package com.darkSProject.ticketBooking.ticket.service;

import com.darkSProject.ticketBooking.ticket.entity.Ticket;
import com.darkSProject.ticketBooking.user.entity.User;
import com.darkSProject.ticketBooking.common.exception.BadRequestException;
import com.darkSProject.ticketBooking.common.exception.ErrorCode;
import com.darkSProject.ticketBooking.ticket.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TicketValidationServiceImpl implements TicketValidationService {

    private final TicketRepository ticketRepository;
    @Override
    public Ticket validateTicketOwnerShip(String ticketId, User user) {

        return ticketRepository
                .findByTicketIdAndUser(
                        ticketId,
                        user
                )

                .orElseThrow(() ->

                        new BadRequestException(
                                "Ticket not found",
                                ErrorCode.TICKET_NOT_FOUND
                        )
                );
    }
}
