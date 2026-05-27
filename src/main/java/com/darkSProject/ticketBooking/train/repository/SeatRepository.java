package com.darkSProject.ticketBooking.train.repository;

import com.darkSProject.ticketBooking.train.entity.Seat;
import jakarta.persistence.LockModeType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat,String> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)

    @Query("""

SELECT s

FROM Seat s

WHERE s.train.trainId = :trainId

AND s.seatId NOT IN (

    SELECT sb.seat.seatId

    FROM SeatBooking sb

    WHERE sb.dateOfTravel = :date

    AND sb.bookingStatus =
        com.darkSProject.ticketBooking.entities.BookingStatus.BOOKED

    AND (

        sb.sourceOrder < :destinationOrder

        AND

        sb.destinationOrder > :sourceOrder
    )
)

ORDER BY s.coach, s.seatNumber

""")
    List<Seat> findAvailableSeats(

            String trainId,

            Date date,

            Integer sourceOrder,

            Integer destinationOrder,

            Pageable pageable
    );
}
