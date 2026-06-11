package com.darkSProject.ticketBooking.payment.consumer;

import com.darkSProject.ticketBooking.payment.config.RabbitMQConfig;
import com.darkSProject.ticketBooking.payment.dto.PaymentEventDTO;
import com.darkSProject.ticketBooking.payment.dto.PaymentResultEventDTO;
import com.darkSProject.ticketBooking.payment.producer.PaymentResultProducer;
import com.rabbitmq.client.Channel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j

public class PaymentConsumer {

    private final PaymentResultProducer
            paymentResultProducer;

    @RabbitListener(
            queues =
                    RabbitMQConfig.PAYMENT_QUEUE
    )

    public void processPayment(

            PaymentEventDTO event,

            Channel channel,

            @Header(
                    AmqpHeaders.DELIVERY_TAG
            )
            long tag,
            @Header(
                    name = "x-death",
                    required = false
            )
            List<Map<String, Object>> xDeath

    ) throws IOException {

        try {

            log.info(
                    "Processing payment for ticket: {}",
                    event.ticketId()
            );

            // simulate payment processing

            Thread.sleep(3000);

            // simulate payment failure

            if(event.amount() > 5000) {

                throw new RuntimeException(
                        "Payment gateway failed"
                );
            }

            // publish success event

            paymentResultProducer.sendPaymentResult(

                    PaymentResultEventDTO.builder()
                            .ticketId(event.ticketId())
                            .userId(event.userId())
                            .eventId(UUID.randomUUID().toString())
                            .success(true)
                            .build()
            );
            log.info(
                    "Publishing PaymentResultEvent for ticket {}",
                    event.ticketId()
            );log.info(
                    "PaymentResultEvent published successfully"
            );

            // ACKNOWLEDGE SUCCESS

            channel.basicAck(
                    tag,
                    false
            );

            log.info(
                    "Payment successful for ticket: {}",
                    event.ticketId()
            );

        } catch (Exception ex) {

            int retryCount = 0;

            if (xDeath != null && !xDeath.isEmpty()) {

                retryCount = ((Long) xDeath.get(0)
                        .get("count"))
                        .intValue();
            }

            log.error(
                    "Payment failed for ticket: {} Retry Count: {}",
                    event.ticketId(),
                    retryCount,
                    ex
            );

            if (retryCount >= 3) {

                log.error(
                        "Max retries exceeded for ticket {}",
                        event.ticketId()
                );

                paymentResultProducer.sendPaymentResult(

                        PaymentResultEventDTO.builder()
                                .ticketId(event.ticketId())
                                .userId(event.userId())
                                .eventId(UUID.randomUUID().toString())
                                .success(false)
                                .build()
                );

                channel.basicReject(
                        tag,
                        false
                );

            } else {

                channel.basicNack(
                        tag,
                        false,
                        false
                );
            }
            channel.basicNack(
                    tag,
                    false,
                    false
            );
        }
    }
}