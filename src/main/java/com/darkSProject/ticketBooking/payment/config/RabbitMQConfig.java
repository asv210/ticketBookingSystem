package com.darkSProject.ticketBooking.payment.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfig  {
    public static final String EXCHANGE =
            "payment.exchange";

    public static final String PAYMENT_QUEUE =
            "payment_queue";

    public static final String ROUTING_KEY =
            "payment.routingKey";
    @Bean
    public Queue paymentQueue() {
        return new Queue(PAYMENT_QUEUE,true);
    }
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(
                EXCHANGE
        );
    }
    @Bean
    public Binding binding(
            Queue paymentQueue,
            TopicExchange exchange
    ) {
        return BindingBuilder
                .bind(paymentQueue)
                .to(exchange)
                .with(ROUTING_KEY);
    }
}
