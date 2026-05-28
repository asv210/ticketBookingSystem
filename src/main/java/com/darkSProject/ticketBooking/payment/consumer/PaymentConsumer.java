package com.darkSProject.ticketBooking.payment.consumer;

import com.darkSProject.ticketBooking.payment.config.RabbitMQConfig;
import com.darkSProject.ticketBooking.payment.dto.PaymentEventDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PaymentConsumer {
    @RabbitListener(
            queues = RabbitMQConfig.PAYMENT_QUEUE
    )
    public void processPayment(
            PaymentEventDTO event
    ){

        log.info(
                "Processing payment for ticket: {} amount: {}",
                event.ticketId(),
                event.amount()
        );

        // simulate payment processing

        try {

            Thread.sleep(3000);

        } catch (InterruptedException e) {

            throw new RuntimeException(e);
        }

        log.info(
                "Payment successful for ticket: {}",
                event.ticketId()
        );
    }
}
