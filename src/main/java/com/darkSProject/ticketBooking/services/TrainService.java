package com.darkSProject.ticketBooking.services;

import com.darkSProject.ticketBooking.dto.AddTrainRequestDTO;
import com.darkSProject.ticketBooking.dto.AddTrainResponseDTO;
import com.darkSProject.ticketBooking.dto.ApiResponse;
import com.darkSProject.ticketBooking.entities.Train;
import com.darkSProject.ticketBooking.factory.TrainFactory;
import com.darkSProject.ticketBooking.repository.TrainRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TrainService {
    private final TrainRepository trainRepository;
    private final TrainFactory trainFactory;
    public ApiResponse<AddTrainResponseDTO> addTrain(
            AddTrainRequestDTO request
    ){
        Train train = trainFactory.createTrain(request);
        trainRepository.save(train);
        return  ApiResponse.<AddTrainResponseDTO>builder().data(
                AddTrainResponseDTO.builder()
                        .trainName(train.getTrainName())
                        .trainId(train.getTrainId())
                        .trainNumber(train.getTrainNo())
                        .build()
                ).success(true)
                .message("Train Added Successfully")
                .build();

    }
}
