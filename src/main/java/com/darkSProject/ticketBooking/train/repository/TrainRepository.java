package com.darkSProject.ticketBooking.train.repository;

import com.darkSProject.ticketBooking.ticket.entity.BookingStatus;
import com.darkSProject.ticketBooking.train.dto.TrainSearchProjection;
import com.darkSProject.ticketBooking.train.entity.Train;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface TrainRepository extends JpaRepository<Train,String> {
    boolean existsByTrainNo(String trainNo);

@Query("""

    SELECT

    t.trainId AS trainId,

    t.trainNo AS trainNo,

    t.trainName AS trainName,

    CAST(
        (
        COUNT(DISTINCT s.seatId)
            -
    COALESCE(
            COUNT(DISTINCT sb.seat.seatId),
                0
                        )
                        )
    AS long
    ) AS availableSeats

    FROM Train t

    JOIN StationSchedule sourceStation
    ON t.trainId = sourceStation.train.trainId

    JOIN StationSchedule destinationStation
    ON t.trainId = destinationStation.train.trainId

    LEFT JOIN Seat s
    ON t.trainId = s.train.trainId

    LEFT JOIN SeatBooking sb
    ON sb.train.trainId = t.trainId
    AND sb.dateOfTravel = :dateOfTravel
    AND sb.bookingStatus = :bookingStatus
    AND (
                    sb.sourceOrder < destinationStation.stationOrder
                            AND
        sb.destinationOrder > sourceStation.stationOrder
            )

    WHERE LOWER(sourceStation.stationName) = LOWER(:source)

    AND LOWER(destinationStation.stationName) = LOWER(:destination)

    AND sourceStation.stationOrder < destinationStation.stationOrder

    GROUP BY
    t.trainId,
    t.trainNo,
    t.trainName

""")
    Page<TrainSearchProjection> searchTrains(
            @Param("source") String source,
            @Param("destination") String destination,
            @Param("dateOfTravel") Date dateOfTravel,
            @Param("bookingStatus") BookingStatus bookingStatus,
            Pageable pageable
    );
    Optional<Train> findByTrainNo(String trainNo);

}
