package com.darkSProject.ticketBooking.payment.repository;

import com.darkSProject.ticketBooking.payment.entity.ProcessedEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcessEventRepository extends JpaRepository<ProcessedEvent,String> {
}
