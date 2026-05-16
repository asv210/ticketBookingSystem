package com.darkSProject.ticketBooking.dto;

import lombok.Builder;

import java.util.Date;
import java.util.List;

@Builder
public record SearchTrainResponseDTO(
        String source,
        String destination,
        Date dateOfTravel,
        Integer page,

        Integer size,

        Long totalElements,

        Integer totalPages,
        List<TrainSearchProjection> trains
        ) {}
