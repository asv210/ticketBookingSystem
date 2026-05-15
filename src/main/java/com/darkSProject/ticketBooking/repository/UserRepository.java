package com.darkSProject.ticketBooking.repository;

import com.darkSProject.ticketBooking.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,String> {
    Optional<User> findByEmail(String name);
}
