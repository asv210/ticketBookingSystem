package com.darkSProject.ticketBooking.services;

import com.darkSProject.ticketBooking.dto.ApiResponse;
import com.darkSProject.ticketBooking.dto.BookTicketRequestDTO;
import com.darkSProject.ticketBooking.dto.SeatInfoDTO;
import com.darkSProject.ticketBooking.dto.TicketResponseDTO;
import com.darkSProject.ticketBooking.entities.*;
import com.darkSProject.ticketBooking.exception.BadRequestException;
import com.darkSProject.ticketBooking.exception.ErrorCode;
import com.darkSProject.ticketBooking.exception.UserNotFoundException;
import com.darkSProject.ticketBooking.repository.SeatRepository;
import com.darkSProject.ticketBooking.repository.TicketRepository;
import com.darkSProject.ticketBooking.repository.TrainRepository;
import com.darkSProject.ticketBooking.repository.UserRepository;
import com.darkSProject.ticketBooking.services.validation.TicketValidationService;
import com.darkSProject.ticketBooking.services.validation.UserValidationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService{

    private final TrainRepository trainRepository;
    private final SeatRepository seatRepository;
    private final UserRepository userRepository;
    private final TicketRepository ticketRepository;
    private final UserValidationService userValidationService;
    private final TicketValidationService ticketValidationService;
    @Transactional
    @Override
    public ApiResponse<TicketResponseDTO> bookTicket(
            BookTicketRequestDTO request
    ){
        User user = userValidationService.getCurrentUser();
        Train train = trainRepository.findByTrainNo(request.trainNo())
                .orElseThrow(()->{
                         throw new BadRequestException("TrainNo not exists", ErrorCode.TRAIN_NOT_EXIST);
                    }
                );
        Pageable pageable = PageRequest.of(0, request.numberOfSeats());
        List<StationSchedule> stations=train.getSchedules();
       Integer sourceOrder = stations.stream()
               .filter(station ->

                   station.getStationName().equalsIgnoreCase(request.source())
               ).findFirst()
               .orElseThrow(
                       () ->   new BadRequestException(

                               "Invalid source station",

                               ErrorCode.INVALID_ROUTE
                       )
               ).getStationOrder();
        Integer destinationOrder = stations.stream()
                .filter(station ->

                        station.getStationName().equalsIgnoreCase(request.destination())
                ).findFirst()
                .orElseThrow(
                        () ->   new BadRequestException(

                                "Invalid source station",

                                ErrorCode.INVALID_ROUTE
                        )
                ).getStationOrder();
        if(sourceOrder > destinationOrder) {
            throw new BadRequestException(
                    "Invalid route selected",
                    ErrorCode.INVALID_ROUTE
            );
        }else if(sourceOrder==destinationOrder){
            throw new BadRequestException(
                    "Source and destination cannot be same",
                     ErrorCode.INVALID_ROUTE
            );
        }
        List<Seat> seats = seatRepository.findAvailableSeats(
                train.getTrainId(),
                request.dateOfTravel(),
                sourceOrder,
                destinationOrder,
                pageable
        );
        if(seats.size()< request.numberOfSeats()){
            throw new BadRequestException("Not enough seats available",ErrorCode.SEATS_UNAVAILABLE);
        }
        Ticket ticket=
                Ticket.builder()
                        .dateOfTravel(request.dateOfTravel())
                        .train(train)
                        .user(user)
                        .destination(request.destination())
                        .source(request.source())
                        .build();

        List<SeatBooking> seatBookings = seats.stream()
                        .map(
                                seat -> {
                                    SeatBooking seatBooking=
                                            SeatBooking.builder()
                                                    .seat(seat)
                                                    .bookingStatus(BookingStatus.BOOKED)
                                                    .ticket(ticket)
                                                    .train(train)
                                                    .dateOfTravel(request.dateOfTravel())
                                                    .source(request.source())
                                                    .destination(request.destination())
                                                    .destinationOrder(destinationOrder)
                                                    .sourceOrder(sourceOrder)
                                                    .build();
                                    return seatBooking;
                                }
                        ).toList();

        ticket.setSeatBookings(
                seatBookings
        );
           Ticket savedTicket= ticketRepository.save(ticket);
           List<SeatInfoDTO> seatInfo= seats.stream()
                   .map(seat ->
                            SeatInfoDTO.builder()
                               .coach(seat.getCoach())
                               .seatNumber(seat.getSeatNumber())
                               .build()
                   ).toList();

           TicketResponseDTO response= TicketResponseDTO.builder()
                   .dateOfTravel(request.dateOfTravel())
                   .ticketId(ticket.getTicketId())
                   .source(request.source())
                   .destination(request.destination())
                   .trainName(train.getTrainName())
                   .trainNumber(train.getTrainName())
                   .seats(seatInfo)
                   .build();
           return ApiResponse.<TicketResponseDTO>builder()
                   .data(response)
                   .success(true)
                   .message("Ticket booked successfully")
                    .build();
    }

    @Override
    public ApiResponse<String> cancelTicket(String ticketId) {
        User user= userValidationService.getCurrentUser();
        Ticket ticket=ticketValidationService.validateTicketOwnerShip(
                ticketId,
                user
        );
        if (ticket.getStatus()==TicketStatus.CANCELLED){
            throw new BadRequestException(

                    "Ticket already cancelled",

                    ErrorCode.TICKET_ALREADY_CANCELLED
            );
        }
        ticket.getSeatBookings()
                .forEach(
                        seatBooking ->
                                seatBooking.setBookingStatus(
                                  BookingStatus.CANCELLED
                                )
                );
        ticket.setStatus(TicketStatus.CANCELLED);
        ticketRepository.save(ticket);
        return ApiResponse.success(
                "Ticket Successfully Deleted",
                null
        );
    }
}
