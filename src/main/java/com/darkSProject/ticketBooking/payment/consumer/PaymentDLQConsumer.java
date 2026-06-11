package com.darkSProject.ticketBooking.payment.consumer;

import com.darkSProject.ticketBooking.payment.config.RabbitMQConfig;
import com.darkSProject.ticketBooking.payment.dto.PaymentEventDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PaymentDLQConsumer {
    @RabbitListener(
            queues =
                    RabbitMQConfig.PAYMENT_DLQ
    )
    public void handleFailedPayments(
            PaymentEventDTO event
    ) {
        log.error(
                "Message moved to DLQ: {}",
                event.ticketId()
        );
    }
}
