package com.darkSProject.ticketBooking.train.factory;

import com.darkSProject.ticketBooking.train.dto.AddTrainRequestDTO;
import com.darkSProject.ticketBooking.train.dto.CoachDTO;
import com.darkSProject.ticketBooking.train.entity.Seat;
import com.darkSProject.ticketBooking.train.entity.StationSchedule;
import com.darkSProject.ticketBooking.train.entity.Train;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TrainFactory{
    public Train createTrain(
            AddTrainRequestDTO request
    ){
        Train train =Train.builder()
                .trainName(request.trainName())
                .trainNo(request.trainNumber())
                .build();
        List<Seat> seatList=new ArrayList<>();
        for(CoachDTO coach: request.coaches()){
            for(int i=1;i<=coach.totalSeats();i++){
                seatList.add(
                        Seat.builder()
                                .train(train)
                                .booked(false)
                                .seatNumber(i)
                                .coach(coach.coachName())
                                .build()
                );
            }
        }
        List<StationSchedule> schedules=request.stations()
                .stream()
                .map(station->
                        StationSchedule.builder()
                                .arrivalTime(station.arrivalTime())
                                .departureTime(station.departureTime())
                                .stationOrder(station.stationOrder())
                                .stationName(station.stationName())
                                .train(train)
                                .build()

                ).toList();
        train.setSeats(seatList);
        train.setSchedules(schedules);
        return train;
    }
}
