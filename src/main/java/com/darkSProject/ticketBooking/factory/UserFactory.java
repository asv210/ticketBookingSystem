package com.darkSProject.ticketBooking.factory;

import com.darkSProject.ticketBooking.dto.SignupRequestDTO;
import com.darkSProject.ticketBooking.entities.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserFactory {
    public User createUser(
            SignupRequestDTO requestDTO,
            BCryptPasswordEncoder passwordEncoder){

            return User.builder()
                    .name(requestDTO.name())
                    .email(requestDTO.email())
                    .hashPassword(
                            passwordEncoder.encode(
                                    requestDTO.password()
                            )
                    ).build();

    }
}
