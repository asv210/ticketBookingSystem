package com.darkSProject.ticketBooking.user.factory;

import com.darkSProject.ticketBooking.auth.dto.SignupRequestDTO;
import com.darkSProject.ticketBooking.user.entity.User;
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
