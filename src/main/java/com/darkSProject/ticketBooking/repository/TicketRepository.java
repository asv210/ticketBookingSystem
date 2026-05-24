package com.darkSProject.ticketBooking.repository;

import com.darkSProject.ticketBooking.entities.Ticket;
import com.darkSProject.ticketBooking.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket,String> {
    Optional<Ticket> findByTicketIdAndUser(String ticketId, User user);
}
