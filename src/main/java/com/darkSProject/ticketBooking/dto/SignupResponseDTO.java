package com.darkSProject.ticketBooking.dto;

import lombok.Builder;

@Builder
public record SignupResponseDTO(
        String userId,
        String email,
        String userName
) {
}
