package com.darkSProject.ticketBooking.train.service;

import com.darkSProject.ticketBooking.common.dto.ApiResponse;
import com.darkSProject.ticketBooking.train.dto.*;
import org.springframework.stereotype.Service;

@Service
public interface TrainService {

    public ApiResponse<AddTrainResponseDTO> addTrain(
            AddTrainRequestDTO request);
    public ApiResponse<SearchTrainResponseDTO> searchTrain(SearchTrainRequestDTO request, int page, int size);
    public ApiResponse<SeatAvailabilityResponseDTO>
    getAvailability(
            SeatAvailabilityRequestDTO request
    );
}
