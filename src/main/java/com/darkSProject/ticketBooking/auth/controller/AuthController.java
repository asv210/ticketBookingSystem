package com.darkSProject.ticketBooking.auth.controller;

import com.darkSProject.ticketBooking.auth.dto.LoginRequestDTO;
import com.darkSProject.ticketBooking.auth.dto.SignupRequestDTO;
import com.darkSProject.ticketBooking.common.dto.ApiResponse;
import com.darkSProject.ticketBooking.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody SignupRequestDTO requestDTO){
        ApiResponse response= userService.signUp(requestDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        response
                );
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDTO loginRequestDTO){
        ApiResponse loginResponseDTO=userService.login(loginRequestDTO);
        return ResponseEntity
                .ok(loginResponseDTO);
    }

}
