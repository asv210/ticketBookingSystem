package com.darkSProject.ticketBooking.train.dto;

import com.darkSProject.ticketBooking.train.entity.StationSchedule;
import lombok.Getter;


public record StationPairDTO(

        StationSchedule sourceStation,

        StationSchedule destinationStation

) {
}
