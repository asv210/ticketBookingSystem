package com.darkSProject.ticketBooking.services.validation;

import com.darkSProject.ticketBooking.entities.Ticket;
import com.darkSProject.ticketBooking.entities.User;

public interface TicketValidationService {
    Ticket validateTicketOwnerShip(
        String ticketId,
        User user
    );
}
