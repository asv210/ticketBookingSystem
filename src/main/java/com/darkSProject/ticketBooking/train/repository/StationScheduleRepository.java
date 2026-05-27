package com.darkSProject.ticketBooking.train.repository;

import com.darkSProject.ticketBooking.train.entity.StationSchedule;
import com.darkSProject.ticketBooking.train.entity.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface StationScheduleRepository extends JpaRepository<StationSchedule,String> {
    Optional<StationSchedule> findByTrainAndStationName(Train train, String source);
}
