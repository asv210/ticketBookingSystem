package com.darkSProject.ticketBooking.services;

import com.darkSProject.ticketBooking.dto.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TrainService {

    public ApiResponse<AddTrainResponseDTO> addTrain(
            AddTrainRequestDTO request);
    public ApiResponse<SearchTrainResponseDTO> searchTrain(SearchTrainRequestDTO request,int page,int size);
}
