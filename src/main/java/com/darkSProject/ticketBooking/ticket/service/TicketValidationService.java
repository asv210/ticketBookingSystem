package com.darkSProject.ticketBooking.ticket.service;

import com.darkSProject.ticketBooking.ticket.entity.Ticket;
import com.darkSProject.ticketBooking.user.entity.User;

public interface TicketValidationService {
    Ticket validateTicketOwnerShip(
        String ticketId,
        User user
    );
}
