package com.darkSProject.ticketBooking.payment.service.stretegy;

import com.darkSProject.ticketBooking.payment.dto.PaymentEventDTO;
import com.darkSProject.ticketBooking.payment.dto.PaymentMethod;
import com.darkSProject.ticketBooking.payment.dto.PaymentResultEventDTO;
import com.darkSProject.ticketBooking.payment.dto.paymentRequestDetails.UpiPaymentRequest;
import com.darkSProject.ticketBooking.payment.gateway.PaymentGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UpiPaymentStrategy
        implements PaymentStrategy {

    private final PaymentGateway paymentGateway;

    @Override
    public PaymentResultEventDTO pay(
            PaymentEventDTO event
    ) {
        UpiPaymentRequest upiPaymentRequest =
                (UpiPaymentRequest) event.paymentDetails();

        boolean success = paymentGateway.charge(upiPaymentRequest).status() ==
                com.darkSProject.ticketBooking.payment.dto.PaymentStatus.SUCCESS;

        return PaymentResultEventDTO.builder()
                .ticketId(event.ticketId())
                .userId(event.userId())
                .eventId(UUID.randomUUID().toString())
                .success(success)
                .build();
    }

    @Override
    public PaymentMethod getSupportedMethod() {
        return PaymentMethod.UPI;
    }
}
