package com.darkSProject.ticketBooking.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


public record SignupRequestDTO(
        @NotBlank(message = "Name is required")
        String name,
        @NotBlank(message = "Password is required")
        String password,
        @Email(message = "Invalid Email")
        @NotBlank(message = "Email is required")
        String email
) {

}