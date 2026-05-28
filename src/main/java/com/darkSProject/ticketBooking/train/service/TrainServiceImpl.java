package com.darkSProject.ticketBooking.train.service;

import com.darkSProject.ticketBooking.common.dto.ApiResponse;
import com.darkSProject.ticketBooking.common.dto.PaginationResponseDTO;
import com.darkSProject.ticketBooking.ticket.entity.BookingStatus;
import com.darkSProject.ticketBooking.train.dto.*;
import com.darkSProject.ticketBooking.train.entity.StationSchedule;
import com.darkSProject.ticketBooking.train.entity.Train;
import com.darkSProject.ticketBooking.common.exception.BadRequestException;
import com.darkSProject.ticketBooking.common.exception.ErrorCode;
import com.darkSProject.ticketBooking.train.factory.TrainFactory;
import com.darkSProject.ticketBooking.train.repository.SeatRepository;
import com.darkSProject.ticketBooking.train.repository.StationScheduleRepository;
import com.darkSProject.ticketBooking.train.repository.TrainRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrainServiceImpl implements TrainService {
    private final TrainRepository trainRepository;
    private final TrainFactory trainFactory;
    private final TrainServiceValidation trainServiceValidation;
    private final StationScheduleRepository stationScheduleRepository;
    private final SeatRepository seatRepository;
    public ApiResponse<AddTrainResponseDTO> addTrain(
            AddTrainRequestDTO request){
        if(trainRepository.existsByTrainNo(
                request.trainNumber()
        )) {

            throw new BadRequestException("TrainNo Already Exists", ErrorCode.TRAIN_EXISTS);
        }
        Train train = trainFactory.createTrain(request);
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
                BookingStatus.BOOKED,
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
    @Override
    public ApiResponse<SeatAvailabilityResponseDTO>
    getAvailability(

            SeatAvailabilityRequestDTO request
    ) {
        Train train = trainServiceValidation.verifyAndFetchTrain(request.trainNo());
        StationPairDTO stationPair=trainServiceValidation.verifyRoute(
                train,
                request.source(),
                request.destination());
        if(stationPair==null){
            throw new BadRequestException(

                    "Route does not exist between '%s' and '%s'"
                            .formatted(request.source(), request.destination()),

                    ErrorCode.INVALID_ROUTE
            );
        }
        Integer destinationOrder=stationPair.destinationStation().getStationOrder();
        Integer sourceOrder=stationPair.sourceStation().getStationOrder();
        List<CoachAvailabilityProjection> projections = seatRepository.findSeatAvailability(
                        request.trainNo(),
                        sourceOrder,
                        destinationOrder,
                        request.dateOfTravel()
                );
        List<CoachAvailabilityDTO> coaches =

                projections.stream()

                        .map(projection ->

                                CoachAvailabilityDTO
                                        .builder()

                                        .coach(
                                                projection.getCoach()
                                        )

                                        .availableSeats(
                                                projection.getAvailableSeats()
                                        )

                                        .build()
                        )

                        .toList();
        Long totalSeats =

                projections.stream()

                        .mapToLong(
                                CoachAvailabilityProjection
                                        ::getAvailableSeats
                        )

                        .sum();
        SeatAvailabilityResponseDTO response =

                SeatAvailabilityResponseDTO
                        .builder()

                        .trainNo(
                                train.getTrainNo()
                        )

                        .trainName(
                                train.getTrainName()
                        )

                        .source(
                                request.source()
                        )

                        .destination(
                                request.destination()
                        )

                        .dateOfTravel(
                                request.dateOfTravel()
                        )

                        .totalAvailableSeats(
                                totalSeats
                        )

                        .coaches(
                                coaches
                        )

                        .build();

        return ApiResponse.success(
                "Availability fetched successfully",
                response
        );
    }
}