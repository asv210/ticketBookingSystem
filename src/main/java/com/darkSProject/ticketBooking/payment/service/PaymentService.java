package com.darkSProject.ticketBooking.payment.service;

import com.darkSProject.ticketBooking.payment.dto.PaymentRequest;
import com.darkSProject.ticketBooking.payment.dto.PaymentResponseDTO;
import com.darkSProject.ticketBooking.payment.service.stretegy.PaymentStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentStrategyFactory factory;

    public PaymentResponseDTO processPayment(
            PaymentRequest request
    ) {

        PaymentStrategy strategy =
                factory.getStrategy(
                        request.getPaymentMethod()
                );

        return strategy.processPayment(
                request
        );
    }
}
