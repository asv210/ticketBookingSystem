package com.darkSProject.ticketBooking.payment.gateway;

import com.darkSProject.ticketBooking.payment.dto.PaymentRequest;
import com.darkSProject.ticketBooking.payment.dto.PaymentResponseDTO;

public interface PaymentGateway {

    PaymentResponseDTO charge(
            PaymentRequest request
    );
}
