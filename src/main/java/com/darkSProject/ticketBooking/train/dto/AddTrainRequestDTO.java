package com.darkSProject.ticketBooking.train.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

import java.util.List;

@Builder
public record AddTrainRequestDTO(
        @NotBlank(message = "Train number required")
        String trainNumber,
        @NotBlank(message = "Train name required")
        String trainName,
        @NotEmpty(message = "At least one coach required")
        @Valid
        List<CoachDTO> coaches,

        @NotEmpty(message = "Stations cannot be empty")
        @Valid
        List<StationDTO> stations
) {}

