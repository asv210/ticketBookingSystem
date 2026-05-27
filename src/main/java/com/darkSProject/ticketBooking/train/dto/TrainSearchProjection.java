package com.darkSProject.ticketBooking.train.dto;


public interface TrainSearchProjection {
    String getTrainId();

    String getTrainNo();

    String getTrainName();

    Number getAvailableSeats();
}
