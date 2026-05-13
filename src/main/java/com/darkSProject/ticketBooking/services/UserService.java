package com.darkSProject.ticketBooking.services;

import com.darkSProject.ticketBooking.dto.AuthResponseDTO;
import com.darkSProject.ticketBooking.dto.SignupRequestDTO;
import com.darkSProject.ticketBooking.entities.User;
import com.darkSProject.ticketBooking.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    public String signUp(SignupRequestDTO requestDTO){

        User user = new User();
        user.setName(requestDTO.getName());

        user.setHashPassword(passwordEncoder.encode(requestDTO.getPassword()));

        userRepository.save(user);

        return "User Registered Successfully";

    }
}
