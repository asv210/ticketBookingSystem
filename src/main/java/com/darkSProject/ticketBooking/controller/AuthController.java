package com.darkSProject.ticketBooking.controller;

import com.darkSProject.ticketBooking.dto.AuthResponseDTO;
import com.darkSProject.ticketBooking.dto.SignupRequestDTO;
import com.darkSProject.ticketBooking.services.UserService;
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
    public ResponseEntity<AuthResponseDTO> signup(@RequestBody SignupRequestDTO requestDTO){
        String response= userService.signUp(requestDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        new AuthResponseDTO(response)
                );
    }

}
