package com.darkSProject.ticketBooking.train.repository;

import com.darkSProject.ticketBooking.train.entity.StationSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StationScheduleRepository extends JpaRepository<StationSchedule,String> {
}
