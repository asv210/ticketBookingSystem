package com.darkSProject.ticketBooking.ticket.repository;

import com.darkSProject.ticketBooking.ticket.dto.TicketResponseDTO;
import com.darkSProject.ticketBooking.ticket.entity.Ticket;
import com.darkSProject.ticketBooking.ticket.entity.TicketStatus;
import com.darkSProject.ticketBooking.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, String> {
    Optional<Ticket> findByTicketIdAndUser(String ticketId, User user);

    @Query("""
            
            SELECT new com.darkSProject.ticketBooking.dto.TicketResponseDTO(
            
                t.ticketId,
            
                tr.trainNo,
            
                tr.trainName,
            
                t.source,
            
                t.destination,
            
                t.dateOfTravel,
            
                t.status
            )
            
            FROM Ticket t
            
            JOIN t.train tr
            
            WHERE t.user.userId = :userId
            AND
            
                (
                    :status IS NULL
                    OR
                    t.status = :status
                )
            ORDER BY t.createdAt DESC
            
            """)
    Page<TicketResponseDTO> getMyBookings(
            String userId,
            TicketStatus status,
            Pageable pageable
    );
}
