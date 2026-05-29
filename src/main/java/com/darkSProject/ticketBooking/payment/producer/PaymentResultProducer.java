package com.darkSProject.ticketBooking.payment.producer;

import com.darkSProject.ticketBooking.payment.config.RabbitMQConfig;
import com.darkSProject.ticketBooking.payment.dto.PaymentResultEventDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentResultProducer {
    private final RabbitTemplate rabbitTemplate;

    public void sendPaymentResult(
            PaymentResultEventDTO event
    ) {

        rabbitTemplate.convertAndSend(

                RabbitMQConfig.PAYMENT_RESULT_QUEUE,

                event
        );
    }
}
