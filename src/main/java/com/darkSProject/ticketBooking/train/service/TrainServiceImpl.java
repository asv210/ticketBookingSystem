package com.darkSProject.ticketBooking.train.service;

import com.darkSProject.ticketBooking.common.dto.ApiResponse;
import com.darkSProject.ticketBooking.common.dto.PaginationResponseDTO;
import com.darkSProject.ticketBooking.train.dto.*;
import com.darkSProject.ticketBooking.train.entity.Train;
import com.darkSProject.ticketBooking.common.exception.BadRequestException;
import com.darkSProject.ticketBooking.common.exception.ErrorCode;
import com.darkSProject.ticketBooking.train.factory.TrainFactory;
import com.darkSProject.ticketBooking.train.repository.TrainRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TrainServiceImpl implements TrainService {
    private final TrainRepository trainRepository;
    private final TrainFactory trainFactory;
    public ApiResponse<AddTrainResponseDTO> addTrain(
            AddTrainRequestDTO request){
        Train train = trainFactory.createTrain(request);
        if(trainRepository.existsByTrainNo(
                request.trainNumber()
        )) {

            throw new BadRequestException("TrainNo Already Exists", ErrorCode.TRAIN_EXISTS);
        }
        trainRepository.save(train);
        return  ApiResponse .<AddTrainResponseDTO>builder().data(
                        AddTrainResponseDTO.builder()
                                .trainName(train.getTrainName())
                                .trainId(train.getTrainId())
                                .trainNumber(train.getTrainNo())
                                .build()
                ).success(true)
                .message("Train Added Successfully")
                .build();
    }

    @Override
    public ApiResponse<SearchTrainResponseDTO> searchTrain(SearchTrainRequestDTO request, int page, int size) {
     Pageable pageable = PageRequest.of(
                        page,
                        size
                );
        Page<TrainSearchProjection> trainPage=trainRepository.searchTrains(request.source(),
                request.destination(),
                request.dateOfTravel(),
                pageable);
        PaginationResponseDTO<TrainSearchProjection> paginatedTrains =

                PaginationResponseDTO
                        .from(trainPage);
        SearchTrainResponseDTO response = SearchTrainResponseDTO.builder()
               .trains(
                       paginatedTrains
               )
               .source(request.source())
               .destination(request.destination())
               .dateOfTravel(request.dateOfTravel())
               .build();
        return ApiResponse.<SearchTrainResponseDTO>builder()
                .success(true)
                .message("Train successfully fetched")
                .data(response)
                .build();
    }
}