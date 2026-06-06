package com.darkSProject.ticketBooking.notification.consumer;

import com.darkSProject.ticketBooking.notification.dto.NotificationDTO;
import com.darkSProject.ticketBooking.notification.service.NotificationService;
import com.darkSProject.ticketBooking.payment.config.RabbitMQConfig;
import com.darkSProject.ticketBooking.payment.dto.PaymentResultEventDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j

@Service
@RequiredArgsConstructor

public class NotificationConsumer {

    private final NotificationService
            notificationService;

    @RabbitListener(
            queues = "notification_queue"
    )
    public void consumePaymentNotification(
            PaymentResultEventDTO event
    ) {

        String message;

        if(event.success()) {

            message =
                    "Payment successful. Ticket confirmed.";

        } else {

            message =
                    "Payment failed.";
        }

        NotificationDTO notification =

                NotificationDTO.builder()

                        .userId(
                                event.userId()
                        )

                        .message(message)

                        .type("PAYMENT")

                        .build();

        notificationService.sendNotification(
                notification
        );

        log.info(
                "Notification sent to user: {}",
                event.userId()
        );
    }
}
