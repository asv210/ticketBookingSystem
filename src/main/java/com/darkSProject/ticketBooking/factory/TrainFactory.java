package com.darkSProject.ticketBooking.factory;

import com.darkSProject.ticketBooking.dto.AddTrainRequestDTO;
import com.darkSProject.ticketBooking.dto.CoachDTO;
import com.darkSProject.ticketBooking.entities.Seat;
import com.darkSProject.ticketBooking.entities.StationSchedule;
import com.darkSProject.ticketBooking.entities.Train;
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
