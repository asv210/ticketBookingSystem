package com.darkSProject.ticketBooking.auth.dto;


public record LoginRequestDTO(
        String email,

        String password
) {}