package com.darkSProject.ticketBooking.ticket.repository;

import com.darkSProject.ticketBooking.ticket.entity.BookingStatus;
import com.darkSProject.ticketBooking.ticket.entity.SeatBooking;
import com.darkSProject.ticketBooking.ticket.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SeatBookingRepository extends JpaRepository<SeatBooking,String> {
    @Modifying
    @Query("""
            
            UPDATE SeatBooking sb
            
            SET sb.bookingStatus = :status
            
            WHERE sb.ticket = :ticket
            
            """)
    void updateBookingStatus(
            @Param("ticket") Ticket ticket,
            @Param("status") BookingStatus status
    );
}
