package com.darkSProject.ticketBooking.repository;

import com.darkSProject.ticketBooking.entities.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat,String> {
}
