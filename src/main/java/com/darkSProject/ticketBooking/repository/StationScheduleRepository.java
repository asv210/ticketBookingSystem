package com.darkSProject.ticketBooking.repository;

import com.darkSProject.ticketBooking.entities.StationSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StationScheduleRepository extends JpaRepository<StationSchedule,String> {
}
