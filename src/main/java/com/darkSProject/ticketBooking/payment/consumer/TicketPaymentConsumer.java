package com.darkSProject.ticketBooking.payment.consumer;

import com.darkSProject.ticketBooking.payment.dto.PaymentResultEventDTO;
import com.darkSProject.ticketBooking.payment.entity.ProcessedEvent;
import com.darkSProject.ticketBooking.payment.repository.ProcessEventRepository;
import com.darkSProject.ticketBooking.ticket.entity.BookingStatus;
import com.darkSProject.ticketBooking.ticket.entity.Ticket;
import com.darkSProject.ticketBooking.ticket.entity.TicketStatus;
import com.darkSProject.ticketBooking.ticket.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class TicketPaymentConsumer {
    private final TicketRepository ticketRepository;
    private final ProcessEventRepository processEventRepository;

    @Transactional
    @RabbitListener(
            queues = "ticket_result_queue"
    )
    public void handlePaymentResult(
            PaymentResultEventDTO event
    ) {
        log.info(
                "Received payment result {}",
                event
        );

        if(event.eventId() == null){
            log.error("Null event id");
            return;
        }

        try {
            processEventRepository.save(
                    ProcessedEvent.builder()
                            .eventId(event.eventId())
                            .processedAt(LocalDateTime.now())
                            .build()
            );
        } catch (DataIntegrityViolationException ex){
            log.info(
                    "Duplicate event ignored {}",
                    event.eventId()
            );
            return;
        }

        Ticket ticket =
                ticketRepository.findById(
                        event.ticketId()
                )
                .orElse(null);

        if(ticket == null){
            log.error(
                    "Ticket not found {}",
                    event.ticketId()
            );
            return;
        }

        if(ticket.getStatus() == TicketStatus.BOOKED
                ||
           ticket.getStatus() == TicketStatus.PAYMENT_FAILED){
            log.info(
                    "Ticket already finalized {}",
                    ticket.getTicketId()
            );
            return;
        }

        try {
            if(event.success()){
                ticket.setStatus(TicketStatus.BOOKED);
                ticket.getSeatBookings()
                        .forEach(seat ->
                                seat.setBookingStatus(BookingStatus.BOOKED)
                        );
            } else {
                ticket.setStatus(TicketStatus.PAYMENT_FAILED);
                ticket.getSeatBookings()
                        .forEach(seat ->
                                seat.setBookingStatus(BookingStatus.PAYMENT_FAILED)
                        );
            }

            log.info(
                    "Ticket updated {}",
                    ticket.getTicketId()
            );
        } catch (Exception ex){
            log.error(
                    "Failed processing payment result",
                    ex
            );
        }
    }
}