package com.darkSProject.ticketBooking.payment.consumer;

import com.darkSProject.ticketBooking.payment.config.RabbitMQConfig;
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
                "Received payment result event: {}",
                event
        );
        // STEP 1
        // check already processed
        if(event.eventId() == null) {

            log.error(
                    "Received event with null eventId"
            );

            return;
        }

        boolean alreadyProcessed =

                processEventRepository
                        .existsById(
                                event.eventId()
                        );

        if(alreadyProcessed) {

            log.info(
                    "Duplicate event ignored: {}",
                    event.eventId()
            );

            return;
        }

        Ticket ticket =
                ticketRepository
                        .findById(event.ticketId())
                        .orElseThrow();
        log.info(
                "Ticket found: {}",
                ticket.getTicketId()
        );
        if(event.success()) {

            ticket.setStatus(
                    TicketStatus.BOOKED
            );

            ticket.getSeatBookings()
                    .forEach(seatBooking ->

                            seatBooking.setBookingStatus(
                                    BookingStatus.BOOKED
                            )
                    );

            log.info(
                    "Ticket confirmed: {}",
                    ticket.getTicketId()
            );

        } else {

            ticket.setStatus(
                    TicketStatus.PAYMENT_FAILED
            );

            ticket.getSeatBookings()
                    .forEach(seatBooking ->

                            seatBooking.setBookingStatus(
                                    BookingStatus.PAYMENT_FAILED
                            )
                    );

            log.info(
                    "Payment failed: {}",
                    ticket.getTicketId()
            );
        }
        processEventRepository.save(

                ProcessedEvent.builder()

                        .eventId(
                                event.eventId()
                        )

                        .processedAt(
                                LocalDateTime.now()
                        )

                        .build()
        );
        log.info(
                "Ticket updated successfully"
        );
        log.info("END OF METHOD");
    }
}