package com.darkSProject.ticketBooking.auth.dto;

import lombok.Builder;

@Builder
public record LoginResponseDTO(
        String token
) {
}
