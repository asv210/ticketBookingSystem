package com.darkSProject.ticketBooking.payment.service.stretegy;

import com.darkSProject.ticketBooking.payment.dto.PaymentMethod;
import com.darkSProject.ticketBooking.payment.dto.PaymentRequestDTO;
import com.darkSProject.ticketBooking.payment.dto.PaymentResponseDTO;
import com.darkSProject.ticketBooking.payment.gateway.PaymentGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpiPaymentStrategy
        implements PaymentStrategy {

    private final PaymentGateway paymentGateway;

    @Override
    public PaymentResponseDTO processPayment(
            PaymentRequestDTO request
    ) {

        return paymentGateway.charge(request);
    }

    @Override
    public PaymentMethod getSupportedMethod() {
        return PaymentMethod.UPI;
    }
}
