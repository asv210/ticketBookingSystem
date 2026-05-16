package com.darkSProject.ticketBooking.repository;

import com.darkSProject.ticketBooking.entities.StationSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StationScheduleRepository extends JpaRepository<StationSchedule,String> {
}
