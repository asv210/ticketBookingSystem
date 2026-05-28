package com.darkSProject.ticketBooking.payment.producer;

import com.darkSProject.ticketBooking.payment.config.RabbitMQConfig;
import com.darkSProject.ticketBooking.payment.dto.PaymentEventDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentProducer {
    private final RabbitTemplate rabbitTemplate;

    public void sendPaymentEvent(
            PaymentEventDTO event
    ) {

        rabbitTemplate.convertAndSend(

                RabbitMQConfig.PAYMENT_QUEUE,

                event
        );
    }
}
