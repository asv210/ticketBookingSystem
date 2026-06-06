package com.darkSProject.ticketBooking.ticket.repository;

import com.darkSProject.ticketBooking.ticket.dto.TicketResponseDTO;
import com.darkSProject.ticketBooking.ticket.entity.BookingStatus;
import com.darkSProject.ticketBooking.ticket.entity.Ticket;
import com.darkSProject.ticketBooking.ticket.entity.TicketStatus;
import com.darkSProject.ticketBooking.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, String> {
    Optional<Ticket> findByTicketIdAndUser(String ticketId, User user);

    @EntityGraph(attributePaths = {
            "train",
            "seatBookings",
            "seatBookings.seat",
            "seatBookings.seat.coach"
    })
    @Query("""
            
            SELECT t
            
            FROM Ticket t
            
            WHERE t.user.userId = :userId
            
            AND (
            
                :status IS NULL
            
                OR
            
                t.status = :status
            )
            
            ORDER BY t.createdAt DESC
            
            """)
    Page<Ticket> getMyBookings(
            String userId,
            TicketStatus status,
            Pageable pageable
    );

    @Query("""
            
            SELECT t
            
            FROM Ticket t
            
            WHERE t.status = :status
            
            AND t.expireAt <= :currentTime
            
            """)
    List<Ticket> findByStatusAndExpireAtLessThanEqual(
            TicketStatus status,
            LocalDateTime currentTime
    );


}
