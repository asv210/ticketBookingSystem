package com.darkSProject.ticketBooking.pricing.service;

import com.darkSProject.ticketBooking.train.entity.Coach;
import com.darkSProject.ticketBooking.train.entity.CoachType;
import com.darkSProject.ticketBooking.train.entity.StationSchedule;
import com.darkSProject.ticketBooking.train.entity.Train;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PricingServiceImpl implements PricingService{
    @Override
    public Double calculateFare(
            Train train,
            int stationsTravel,
            CoachType coachType,
            int numberOfSeats
    ) {


        Coach coach =
                train.getCoaches()
                        .stream()
                        .filter(c ->
                                c.getCoachType() == coachType
                        )
                        .findFirst()
                        .orElseThrow();

        return
                stationsTravel
                        *
                        coach.getFarePerStation()
                        *
                        numberOfSeats;
    }
}
