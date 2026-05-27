package com.darkSProject.ticketBooking.train.service;

import com.darkSProject.ticketBooking.common.exception.BadRequestException;
import com.darkSProject.ticketBooking.common.exception.ErrorCode;
import com.darkSProject.ticketBooking.train.dto.StationPairDTO;
import com.darkSProject.ticketBooking.train.entity.StationSchedule;
import com.darkSProject.ticketBooking.train.entity.Train;
import com.darkSProject.ticketBooking.train.repository.StationScheduleRepository;
import com.darkSProject.ticketBooking.train.repository.TrainRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class TrainServiceValidationImpl implements TrainServiceValidation{

    private final TrainRepository trainRepository;
    private final StationScheduleRepository stationScheduleRepository;
    public Train verifyAndFetchTrain(String trainNo){
        return trainRepository
                .findByTrainNo(
                        trainNo
                )
                .orElseThrow(() ->

                        new BadRequestException(
                                "Train not found",
                                ErrorCode.TRAIN_NOT_EXIST
                        )
                );
    }

    public StationSchedule verifyAndFetchStation(Train train, String station){
        return stationScheduleRepository
                .findByTrainAndStationName(
                        train,
                        station
                ).orElseThrow(()->
                        new BadRequestException(
                                "Station '%s' does not exist in train route"
                                        .formatted(station),
                                ErrorCode.STATION_DOES_NOT_EXIST
                        )
                );
    }

    public StationPairDTO verifyRoute(Train train, String source, String destination){
        StationSchedule sourceStation = verifyAndFetchStation(train, source);
        StationSchedule destinationStation=verifyAndFetchStation(train,destination);
        if (sourceStation.getStationOrder()
                        >=
                        destinationStation.getStationOrder()) {

            return null;
        }
        return new StationPairDTO(
                sourceStation,
                destinationStation
        );
    }
}
