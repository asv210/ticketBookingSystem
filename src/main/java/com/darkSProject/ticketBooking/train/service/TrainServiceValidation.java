package com.darkSProject.ticketBooking.train.service;

import com.darkSProject.ticketBooking.train.dto.StationPairDTO;
import com.darkSProject.ticketBooking.train.entity.StationSchedule;
import com.darkSProject.ticketBooking.train.entity.Train;

public interface TrainServiceValidation {
    public Train verifyAndFetchTrain(String trainNo);
    public StationSchedule verifyAndFetchStation(Train train, String station);
    public StationPairDTO verifyRoute(Train train, String source, String destination);
}
