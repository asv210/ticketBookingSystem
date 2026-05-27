package com.darkSProject.ticketBooking.train.dto;

import lombok.Builder;

@Builder
public record AddTrainResponseDTO(
        String trainId,
        String trainName,
        String trainNumber
) {
}
