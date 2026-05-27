package com.darkSProject.ticketBooking.train.repository;

import com.darkSProject.ticketBooking.train.entity.Coach;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoachRepository extends JpaRepository<Coach,String> {
}
