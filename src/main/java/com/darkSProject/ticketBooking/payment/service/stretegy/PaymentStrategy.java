package com.darkSProject.ticketBooking.payment.service.stretegy;

import com.darkSProject.ticketBooking.payment.dto.PaymentMethod;
import com.darkSProject.ticketBooking.payment.dto.PaymentRequest;
import com.darkSProject.ticketBooking.payment.dto.PaymentResponseDTO;

public interface PaymentStrategy {

    PaymentResponseDTO processPayment(
            PaymentRequest request
    );

    PaymentMethod getSupportedMethod();
}
