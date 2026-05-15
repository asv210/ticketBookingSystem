package com.darkSProject.ticketBooking.services;

import com.darkSProject.ticketBooking.dto.*;
import com.darkSProject.ticketBooking.entities.User;
import com.darkSProject.ticketBooking.exception.InvalidCredentialsException;
import com.darkSProject.ticketBooking.exception.UserNotFoundException;
import com.darkSProject.ticketBooking.factory.UserFactory;
import com.darkSProject.ticketBooking.jwt.JwtService;
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

    private final UserFactory userFactory;
    private final JwtService jwtService;

    public ApiResponse<SignupResponseDTO> signUp(SignupRequestDTO requestDTO){

        User user = userFactory.createUser(requestDTO,passwordEncoder);

        userRepository.save(user);

        return ApiResponse.<SignupResponseDTO>builder()
                .data(
                        SignupResponseDTO.builder()
                                .userId(user.getUserId())
                                .userName(user.getName())
                                .email(user.getEmail())
                                .build()
                )
                .success(true)
                .message("User Created Successfully")
                .build();

    }

    public ApiResponse login(
            LoginRequestDTO loginRequestDTO
    ){
        User user = userRepository
                .findByEmail(loginRequestDTO.email())
                .orElseThrow(
                        UserNotFoundException::new
                );

        boolean isPasswordCorrect = passwordEncoder
                .matches(
                        loginRequestDTO.password(),
                        user.getHashPassword()
                );
        if(!isPasswordCorrect){
            throw new InvalidCredentialsException();
        }
        String token = jwtService.generateToken(user.getEmail());
        return ApiResponse.<LoginResponseDTO>builder()
                .data(
                        LoginResponseDTO.builder()
                                .token(token)
                                .build()
                ).message("Login Successful")
                .success(true)
                .build();
    }
}
