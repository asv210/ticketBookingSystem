package com.darkSProject.ticketBooking.ticket.service;

import com.darkSProject.ticketBooking.common.dto.ApiResponse;
import com.darkSProject.ticketBooking.common.dto.PaginationResponseDTO;
import com.darkSProject.ticketBooking.common.exception.BadRequestException;
import com.darkSProject.ticketBooking.common.exception.ErrorCode;
import com.darkSProject.ticketBooking.ticket.dto.BookTicketRequestDTO;
import com.darkSProject.ticketBooking.ticket.dto.SeatInfoDTO;
import com.darkSProject.ticketBooking.ticket.dto.TicketResponseDTO;
import com.darkSProject.ticketBooking.ticket.entity.BookingStatus;
import com.darkSProject.ticketBooking.ticket.entity.TicketStatus;
import com.darkSProject.ticketBooking.train.dto.StationPairDTO;
import com.darkSProject.ticketBooking.train.entity.Seat;
import com.darkSProject.ticketBooking.train.entity.SeatBooking;
import com.darkSProject.ticketBooking.train.entity.StationSchedule;
import com.darkSProject.ticketBooking.train.repository.SeatRepository;
import com.darkSProject.ticketBooking.ticket.repository.TicketRepository;
import com.darkSProject.ticketBooking.train.entity.Train;
import com.darkSProject.ticketBooking.train.repository.TrainRepository;
import com.darkSProject.ticketBooking.ticket.entity.Ticket;
import com.darkSProject.ticketBooking.train.service.TrainServiceValidation;
import com.darkSProject.ticketBooking.user.entity.User;
import com.darkSProject.ticketBooking.user.repository.UserRepository;
import com.darkSProject.ticketBooking.user.service.UserValidationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TrainRepository trainRepository;
    private final SeatRepository seatRepository;
    private final UserRepository userRepository;
    private final TicketRepository ticketRepository;
    private final UserValidationService userValidationService;
    private final TicketValidationService ticketValidationService;
    private final TrainServiceValidation trainServiceValidation;
    @Transactional
    @Override
    public ApiResponse<TicketResponseDTO> bookTicket(
            BookTicketRequestDTO request
    ){
        User user = userValidationService.getCurrentUser();
        Train train = trainServiceValidation.verifyAndFetchTrain(request.trainNo());
        Pageable pageable = PageRequest.of(0, request.numberOfSeats());
        StationPairDTO stationPair=trainServiceValidation.verifyRoute(train,request.source(),request.destination());

            if(stationPair==null){
                throw new BadRequestException(

                        "Route does not exist between '%s' and '%s'"
                                .formatted(request.source(), request.destination()),

                        ErrorCode.INVALID_ROUTE
                );
            }
        Integer destinationOrder=stationPair.destinationStation().getStationOrder();
        Integer sourceOrder=stationPair.sourceStation().getStationOrder();
        List<Seat> seats = seatRepository.findAvailableSeats(
                train.getTrainId(),
                request.dateOfTravel(),
                sourceOrder,
                destinationOrder,
                BookingStatus.BOOKED,
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
                               .coachName(seat.getCoach().getCoachName())
                               .seatNumber(seat.getSeatNumber())
                               .build()
                   ).toList();

           TicketResponseDTO response= TicketResponseDTO.builder()
                   .dateOfTravel(request.dateOfTravel())
                   .ticketId(ticket.getTicketId())
                   .source(request.source())
                   .destination(request.destination())
                   .trainName(train.getTrainName())
                   .trainNo(train.getTrainNo())
                   .seats(seatInfo)
                   .status(TicketStatus.BOOKED)
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
                "Ticket cancelled successfully",
                ticketId
        );
    }

    @Override
    public ApiResponse<PaginationResponseDTO<TicketResponseDTO>>
    myBookings(int page, int size, TicketStatus status) {

        User user = userValidationService.getCurrentUser();

        Pageable pageable = PageRequest.of(page, size);

        Page<Ticket> tickets = ticketRepository.getMyBookings(
                user.getUserId(),
                status,
                pageable
        );

        Page<TicketResponseDTO> response = tickets.map(ticket -> {

            List<SeatInfoDTO> seats = ticket
                    .getSeatBookings()
                    .stream()
                    .map(seatBooking ->

                            SeatInfoDTO.builder()
                                    .coachName(
                                            seatBooking
                                                    .getSeat()
                                                    .getCoach()
                                                    .getCoachName()
                                    )
                                    .seatNumber(
                                            seatBooking
                                                    .getSeat()
                                                    .getSeatNumber()
                                    )
                                    .build()
                    )
                    .toList();

            return TicketResponseDTO.builder()
                    .ticketId(ticket.getTicketId())
                    .trainNo(ticket.getTrain().getTrainNo())
                    .trainName(ticket.getTrain().getTrainName())
                    .source(ticket.getSource())
                    .destination(ticket.getDestination())
                    .dateOfTravel(ticket.getDateOfTravel())
                    .status(ticket.getStatus())
                    .seats(seats)
                    .build();
        });

        return ApiResponse.success(
                "Bookings fetched successfully",
                PaginationResponseDTO.from(response)
        );
    }
}
