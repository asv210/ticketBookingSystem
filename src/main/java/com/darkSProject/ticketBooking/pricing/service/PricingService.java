package com.darkSProject.ticketBooking.pricing.service;

import com.darkSProject.ticketBooking.train.entity.CoachType;
import com.darkSProject.ticketBooking.train.entity.Train;

public interface PricingService {
    Double calculateFare(
            Train train,
            int stationTravel,
            CoachType coachType,
            int numberOfSeats
    );
}
