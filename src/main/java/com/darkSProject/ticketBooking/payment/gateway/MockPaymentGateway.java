package com.darkSProject.ticketBooking.payment.gateway;

import com.darkSProject.ticketBooking.payment.dto.PaymentRequestDTO;
import com.darkSProject.ticketBooking.payment.dto.PaymentResponseDTO;
import org.springframework.stereotype.Service;

@Service
public class MockPaymentGateway
        implements PaymentGateway {

    @Override
    public PaymentResponseDTO charge(
            PaymentRequestDTO request
    ) {

        boolean success =
                request.amount() <= 5000;

        return PaymentResponseDTO.builder()
                .success(success)
                .transactionId(
                        UUID.randomUUID().toString()
                )
                .build();
    }
}
