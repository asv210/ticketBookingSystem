package com.darkSProject.ticketBooking.dto;

import lombok.Builder;

@Builder
public record AddTrainResponseDTO(
        String trainId,
        String trainName,
        String trainNumber
) {
}
