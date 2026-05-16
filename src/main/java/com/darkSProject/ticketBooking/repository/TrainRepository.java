package com.darkSProject.ticketBooking.repository;

import com.darkSProject.ticketBooking.entities.Train;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainRepository extends JpaRepository<Train,String> {
    boolean existsByTrainNo(String trainNo);
}
