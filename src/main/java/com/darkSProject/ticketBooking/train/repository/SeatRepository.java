package com.darkSProject.ticketBooking.train.repository;

import com.darkSProject.ticketBooking.ticket.entity.BookingStatus;
import com.darkSProject.ticketBooking.train.dto.CoachAvailabilityProjection;
import com.darkSProject.ticketBooking.train.entity.CoachType;
import com.darkSProject.ticketBooking.train.entity.Seat;
import jakarta.persistence.LockModeType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat, String> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("""
            
            SELECT s
            
            FROM Seat s
            
            WHERE s.train.trainId = :trainId
            
            AND s.coach.coachType = :coachType
            AND s.seatId NOT IN (
            
                SELECT sb.seat.seatId
            
                FROM SeatBooking sb
            
                WHERE sb.dateOfTravel = :date
            
                AND sb.bookingStatus IN :statuses
            
                AND (
            
                    sb.sourceOrder < :destinationOrder
            
                    AND
            
                    sb.destinationOrder > :sourceOrder
                )
            
            )
            
            ORDER BY s.coach.coachName, s.seatNumber
            
            """)
    List<Seat> findAvailableSeats(
            @Param("trainId") String trainId,
            @Param("date") LocalDateTime date,
            @Param("coachType") CoachType coachType,
            @Param("sourceOrder") Integer sourceOrder,
            @Param("destinationOrder") Integer destinationOrder,
            @Param("statuses") List<BookingStatus> statuses,
            Pageable pageable
    );

    @Query("""
            
            SELECT
            
                s.coach.coachName AS coach,
            
                COUNT(s) AS availableSeats
            
            FROM Seat s
            
            WHERE s.train.trainNo = :trainNo
            
            AND s.seatId NOT IN (
            
                SELECT sb.seat.seatId
            
                FROM SeatBooking sb
            
                WHERE
            
                    sb.dateOfTravel = :dateOfTravel
            
                AND
            
                    sb.bookingStatus IN :statuses
            
                AND
            
                    sb.train.trainNo = :trainNo
            
                AND (
            
                        sb.sourceOrder < :destinationOrder
            
                    AND
            
                        sb.destinationOrder > :sourceOrder
                )
            )
            
            GROUP BY s.coach.coachName
            
            ORDER BY s.coach.coachName
            
            """)
    List<CoachAvailabilityProjection>
    findSeatAvailability(
            @Param("trainNo") String trainNo,
            @Param("sourceOrder") Integer sourceOrder,
            @Param("destinationOrder") Integer destinationOrder,
            @Param("dateOfTravel") Date dateOfTravel,
            @Param("statuses") List<BookingStatus> statuses
    );
}
