package com.darkSProject.ticketBooking.repository;

import com.darkSProject.ticketBooking.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket,String> {
}
