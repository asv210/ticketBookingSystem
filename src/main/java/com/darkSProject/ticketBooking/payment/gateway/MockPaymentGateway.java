package com.darkSProject.ticketBooking.payment.gateway;

import com.darkSProject.ticketBooking.payment.dto.PaymentRequest;
import com.darkSProject.ticketBooking.payment.dto.PaymentResponseDTO;
import com.darkSProject.ticketBooking.payment.dto.PaymentStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class MockPaymentGateway
        implements PaymentGateway {

    @Override
    public PaymentResponseDTO charge(
            PaymentRequest request
    ) {

        boolean success =
                request.getAmount() <= 5000;

        return PaymentResponseDTO.builder()
                .paymentId(UUID.randomUUID().toString())
                .status(success ? PaymentStatus.SUCCESS : PaymentStatus.FAILED)
                .transactionId(
                        UUID.randomUUID().toString()
                )
                .gatewayReferenceId(UUID.randomUUID().toString())
                .message(success ? "Payment successful" : "Payment failed")
                .timestamp(LocalDateTime.now())
                .build();
    }
}
