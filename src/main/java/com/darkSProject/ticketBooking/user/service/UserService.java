package com.darkSProject.ticketBooking.user.service;

import com.darkSProject.ticketBooking.auth.dto.LoginRequestDTO;
import com.darkSProject.ticketBooking.auth.dto.LoginResponseDTO;
import com.darkSProject.ticketBooking.auth.dto.SignupRequestDTO;
import com.darkSProject.ticketBooking.auth.dto.SignupResponseDTO;
import com.darkSProject.ticketBooking.common.dto.ApiResponse;
import com.darkSProject.ticketBooking.user.entity.User;
import com.darkSProject.ticketBooking.common.exception.ErrorCode;
import com.darkSProject.ticketBooking.common.exception.InvalidCredentialsException;
import com.darkSProject.ticketBooking.common.exception.UserNotFoundException;
import com.darkSProject.ticketBooking.user.factory.UserFactory;
import com.darkSProject.ticketBooking.jwt.JwtService;
import com.darkSProject.ticketBooking.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    private final UserFactory userFactory;
    private final JwtService jwtService;
    @Transactional
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
                .orElseThrow(()->{
                            throw new UserNotFoundException("User Not Found", ErrorCode.USER_NOT_FOUND);
                        }
                );

        boolean isPasswordCorrect = passwordEncoder
                .matches(
                        loginRequestDTO.password(),
                        user.getHashPassword()
                );
        if(!isPasswordCorrect){
            throw new InvalidCredentialsException("Invalid credentials",ErrorCode.INVALID_CREDENTIALS);
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
