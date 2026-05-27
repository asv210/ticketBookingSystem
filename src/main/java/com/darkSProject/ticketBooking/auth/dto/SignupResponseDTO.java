package com.darkSProject.ticketBooking.auth.dto;

import lombok.Builder;

@Builder
public record SignupResponseDTO(
        String userId,
        String email,
        String userName
) {
}
