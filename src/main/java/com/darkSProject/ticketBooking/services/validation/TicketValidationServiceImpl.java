package com.darkSProject.ticketBooking.services.validation;

import com.darkSProject.ticketBooking.entities.Ticket;
import com.darkSProject.ticketBooking.entities.User;
import com.darkSProject.ticketBooking.exception.BadRequestException;
import com.darkSProject.ticketBooking.exception.ErrorCode;
import com.darkSProject.ticketBooking.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TicketValidationServiceImpl implements TicketValidationService{

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
