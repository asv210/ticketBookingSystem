package com.darkSProject.ticketBooking.payment.service.stretegy;

import com.darkSProject.ticketBooking.payment.dto.PaymentMethod;
import com.darkSProject.ticketBooking.payment.dto.PaymentRequest;
import com.darkSProject.ticketBooking.payment.dto.PaymentResponseDTO;
import com.darkSProject.ticketBooking.payment.dto.paymentRequestDetails.CardPaymentRequest;
import com.darkSProject.ticketBooking.payment.gateway.PaymentGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CardPaymentStrategy
        implements PaymentStrategy {

    private final PaymentGateway paymentGateway;

    @Override
    public PaymentResponseDTO processPayment(
            PaymentRequest request
    ) {
        CardPaymentRequest cardRequest =
                (CardPaymentRequest) request;
        return paymentGateway.charge(request);
    }

    @Override
    public PaymentMethod getSupportedMethod() {
        return PaymentMethod.CREDIT_CARD;
    }
}