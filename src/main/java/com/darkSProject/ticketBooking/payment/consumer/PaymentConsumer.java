package com.darkSProject.ticketBooking.payment.consumer;

import com.darkSProject.ticketBooking.payment.config.RabbitMQConfig;
import com.darkSProject.ticketBooking.payment.dto.PaymentEventDTO;
import com.darkSProject.ticketBooking.payment.dto.PaymentResultEventDTO;
import com.darkSProject.ticketBooking.payment.producer.PaymentResultProducer;
import com.darkSProject.ticketBooking.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentConsumer {

    private final PaymentResultProducer paymentResultProducer;
    private final PaymentService paymentService;

    @RabbitListener(
            queues = RabbitMQConfig.PAYMENT_QUEUE
    )
    public void processPayment(
            PaymentEventDTO event,
            @Header(name = "x-death", required = false) List<Map<String, Object>> xDeath
    ) {

        try {
            log.info("Processing payment for ticket: {}", event.ticketId());

            PaymentResultEventDTO result = paymentService.process(event);

            paymentResultProducer.sendPaymentResult(result);

            log.info("PaymentResultEvent published successfully for ticket: {}", event.ticketId());
            log.info("Payment processed successfully for ticket: {}", event.ticketId());

        } catch (Exception ex) {
            int retryCount = 0;

            if (xDeath != null && !xDeath.isEmpty()) {
                retryCount = ((Long) xDeath.get(0).get("count")).intValue();
            }

            log.error("Payment failed for ticket: {} Retry Count: {}", event.ticketId(), retryCount, ex);

            if (retryCount >= 3) {
                log.error("Max retries exceeded for ticket {}", event.ticketId());

                paymentResultProducer.sendPaymentResult(
                        PaymentResultEventDTO.builder()
                                .ticketId(event.ticketId())
                                .userId(event.userId())
                                .eventId(java.util.UUID.randomUUID().toString())
                                .success(false)
                                .build()
                );
            }
        }
    }
}