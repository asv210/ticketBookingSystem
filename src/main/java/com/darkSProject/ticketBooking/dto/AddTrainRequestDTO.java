package com.darkSProject.ticketBooking.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record AddTrainRequestDTO(
        String trainNumber,
        String trainName,
        List<CoachDTO> coaches,
        List<StationDTO> stations
) {}

