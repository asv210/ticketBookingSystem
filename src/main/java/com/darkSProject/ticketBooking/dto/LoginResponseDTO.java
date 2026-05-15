package com.darkSProject.ticketBooking.dto;

import lombok.Builder;

@Builder
public record LoginResponseDTO(
        String token
) {
}
