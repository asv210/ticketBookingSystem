package com.darkSProject.ticketBooking.payment.producer;

import com.darkSProject.ticketBooking.payment.config.RabbitMQConfig;
import com.darkSProject.ticketBooking.payment.dto.PaymentEventDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Queue;

@Service
@RequiredArgsConstructor
public class PaymentProducer {
    private final RabbitTemplate rabbitTemplate;


    public void sendPaymentEvent(
            PaymentEventDTO event
    ) {

        rabbitTemplate.convertAndSend(
                "payment_result_exchange",
                "",
                event
        );
    }

}
