package com.darkSProject.ticketBooking.payment.service;

import com.darkSProject.ticketBooking.payment.dto.PaymentMethod;
import com.darkSProject.ticketBooking.common.exception.UnsupportedPaymentMethodException;
import com.darkSProject.ticketBooking.payment.service.stretegy.PaymentStrategy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class PaymentStrategyFactory {

    private final Map<PaymentMethod,
                PaymentStrategy> strategies;

    public PaymentStrategyFactory(
            List<PaymentStrategy> strategyList
    ) {

        strategies =
                strategyList.stream()
                        .collect(Collectors.toMap(
                                PaymentStrategy::getSupportedMethod,
                                Function.identity()
                        ));
    }

    public PaymentStrategy getStrategy(
            PaymentMethod method
    ) {

        PaymentStrategy strategy =
                strategies.get(method);

        if(strategy == null) {

            throw new UnsupportedPaymentMethodException(
                    "Payment method not supported"
            );
        }

        return strategy;
    }
}
