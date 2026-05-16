package com.darkSProject.ticketBooking.repository;

import com.darkSProject.ticketBooking.dto.SearchTrainResponseDTO;
import com.darkSProject.ticketBooking.dto.TrainSearchProjection;
import com.darkSProject.ticketBooking.entities.Train;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface TrainRepository extends JpaRepository<Train,String> {
    boolean existsByTrainNo(String trainNo);
    @Query("""

SELECT 

    t.trainId,

    t.trainNo,

    t.trainName,

    (
        COUNT(DISTINCT s)
        -
        COUNT(DISTINCT tk)
    )


FROM Train t

JOIN t.schedules sourceStation

JOIN t.schedules destinationStation

LEFT JOIN t.seats s

LEFT JOIN Ticket tk
    ON tk.train = t
    AND tk.dateOfTravel = :date

WHERE

    LOWER(sourceStation.stationName)
        =
    LOWER(:source)

AND

    LOWER(destinationStation.stationName)
        =
    LOWER(:destination)

AND

    sourceStation.stationOrder
        <
    destinationStation.stationOrder

GROUP BY

    t.trainId,
    t.trainNo,
    t.trainName

""")
    Page<TrainSearchProjection> searchTrains(

            String source,

            String destination,

            Date date,

            Pageable pageable
    );

    Optional<Train> findByTrainNo(String trainNo);

}
