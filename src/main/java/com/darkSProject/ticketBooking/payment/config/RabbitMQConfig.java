package com.darkSProject.ticketBooking.payment.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfig  {
    public static final String PAYMENT_DLQ =
            "payment_dlq";

    public static final String PAYMENT_QUEUE =
            "payment_queue";

    public static final String PAYMENT_RESULT_QUEUE =
            "payment_result_queue";

    public static final String
            PAYMENT_RETRY_QUEUE =
            "payment_retry_queue";
    @Bean
    public Queue paymentQueue() {

        return QueueBuilder
                .durable(PAYMENT_QUEUE)

                .withArgument(
                        "x-dead-letter-exchange",
                        ""
                )

                .withArgument(
                        "x-dead-letter-routing-key",
                        PAYMENT_RETRY_QUEUE
                )

                .build();
    }
    @Bean
    public Queue paymentRetryQueue() {

        return QueueBuilder

                .durable(PAYMENT_RETRY_QUEUE)

                // retry after 10 seconds

                .withArgument(
                        "x-message-ttl",
                        10000
                )

                // send back to main queue

                .withArgument(
                        "x-dead-letter-exchange",
                        ""
                )

                .withArgument(
                        "x-dead-letter-routing-key",
                        PAYMENT_QUEUE
                )

                .build();
    }
    @Bean
    public Queue paymentDeadLetterQueue() {

        return new Queue(
                PAYMENT_DLQ,
                true
        );
    }
    @Bean
    public Queue paymentResultQueue() {

        return new Queue(
                PAYMENT_RESULT_QUEUE,
                true
        );
    }
}
