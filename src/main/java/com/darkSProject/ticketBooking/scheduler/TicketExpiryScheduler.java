package com.darkSProject.ticketBooking.scheduler;

import com.darkSProject.ticketBooking.ticket.entity.BookingStatus;
import com.darkSProject.ticketBooking.ticket.entity.Ticket;
import com.darkSProject.ticketBooking.ticket.entity.TicketStatus;
import com.darkSProject.ticketBooking.ticket.repository.SeatBookingRepository;
import com.darkSProject.ticketBooking.ticket.repository.TicketRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class TicketExpiryScheduler {

    private final TicketRepository ticketRepository;

    private final SeatBookingRepository seatBookingRepository;

    @Scheduled(fixedRateString = "${ticket.expiry.scheduler-rate}")
    @Transactional
    public void expireTicket(){
        log.info(
                "Running ticket expiry scheduler"
        );

        List<Ticket> expiredTickets =ticketRepository
                .findByStatusAndExpireAtLessThanEqual(

                        TicketStatus.PENDING_PAYMENT,

                        LocalDateTime.now()
                );
        if(expiredTickets.isEmpty()) {

            log.info(
                    "No expired tickets found"
            );

            return;
        }
        for(Ticket ticket : expiredTickets) {

            ticket.setStatus(
                    TicketStatus.EXPIRED
            );

            seatBookingRepository
                    .updateBookingStatus(

                            ticket,

                            BookingStatus.EXPIRED
                    );

            log.info(
                    "Ticket expired : {}",
                    ticket.getTicketId()
            );
        }

        ticketRepository.saveAll(
                expiredTickets
        );
    }
}
