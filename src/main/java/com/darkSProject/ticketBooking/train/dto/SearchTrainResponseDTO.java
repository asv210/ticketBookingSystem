package com.darkSProject.ticketBooking.train.dto;

import com.darkSProject.ticketBooking.common.dto.PaginationResponseDTO;
import lombok.Builder;

import java.util.Date;

@Builder
public record SearchTrainResponseDTO(
        String source,
        String destination,
        Date dateOfTravel,
        PaginationResponseDTO<TrainSearchProjection> trains
        ) {}
