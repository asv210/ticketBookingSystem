package com.darkSProject.ticketBooking.train.factory;

import com.darkSProject.ticketBooking.train.dto.AddTrainRequestDTO;
import com.darkSProject.ticketBooking.train.dto.CoachDTO;
import com.darkSProject.ticketBooking.train.entity.Coach;
import com.darkSProject.ticketBooking.train.entity.Seat;
import com.darkSProject.ticketBooking.train.entity.StationSchedule;
import com.darkSProject.ticketBooking.train.entity.Train;
import com.darkSProject.ticketBooking.train.repository.CoachRepository;
import com.darkSProject.ticketBooking.train.repository.SeatRepository;
import com.darkSProject.ticketBooking.train.repository.TrainRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class TrainFactory{
    private final CoachRepository coachRepository;
    private final TrainRepository trainRepository;
    private final SeatRepository seatRepository;
    @Transactional
    public Train createTrain(
            AddTrainRequestDTO request
    ) {

        Train train = Train.builder()
                .trainName(request.trainName())
                .trainNo(request.trainNumber())
                .build();

        // SAVE TRAIN FIRST
        Train savedTrain =
                trainRepository.save(train);

        List<Seat> seatList = new ArrayList<>();

        List<Coach> coachList = new ArrayList<>();

        for (CoachDTO coachDTO : request.coaches()) {

            Coach coach = Coach.builder()
                    .coachName(coachDTO.coachName())
                    .train(savedTrain)
                    .build();

            Coach savedCoach =
                    coachRepository.save(coach);

            coachList.add(savedCoach);

            for (int i = 1; i <= coachDTO.totalSeats(); i++) {

                seatList.add(

                        Seat.builder()
                                .train(savedTrain)
                                .booked(false)
                                .seatNumber(i)
                                .coach(savedCoach)
                                .build()
                );
            }
        }

        List<StationSchedule> schedules =

                request.stations()
                        .stream()
                        .map(station ->

                                StationSchedule.builder()
                                        .arrivalTime(
                                                station.arrivalTime()
                                        )
                                        .departureTime(
                                                station.departureTime()
                                        )
                                        .stationOrder(
                                                station.stationOrder()
                                        )
                                        .stationName(
                                                station.stationName()
                                        )
                                        .train(savedTrain)
                                        .build()

                        )
                        .toList();

        savedTrain.setCoaches(coachList);

        savedTrain.setSeats(seatList);

        savedTrain.setSchedules(schedules);
        return savedTrain;
    }
}
