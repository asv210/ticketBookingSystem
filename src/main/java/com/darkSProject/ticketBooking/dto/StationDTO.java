package com.darkSProject.ticketBooking.dto;

import java.util.Date;

public record StationDTO(
        String stationName,

        Integer stationOrder,

        Date arrivalTime,

        Date departureTime
){}
