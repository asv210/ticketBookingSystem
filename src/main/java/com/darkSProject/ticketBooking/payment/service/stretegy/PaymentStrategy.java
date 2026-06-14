package com.darkSProject.ticketBooking.payment.service.stretegy;

import com.darkSProject.ticketBooking.payment.dto.PaymentEventDTO;
import com.darkSProject.ticketBooking.payment.dto.PaymentMethod;
import com.darkSProject.ticketBooking.payment.dto.PaymentResultEventDTO;

public interface PaymentStrategy {

    PaymentResultEventDTO pay(
            PaymentEventDTO event
    );

    PaymentMethod getSupportedMethod();
}
