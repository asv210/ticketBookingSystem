package com.darkSProject.ticketBooking.repository;

import com.darkSProject.ticketBooking.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
    User findByName(String name);
}
