package com.darkSProject.ticketBooking.payment.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class PaymentRequest {

    private String ticketId;

    private String userId;

    private Double amount;

    private String currency;

    private PaymentMethod paymentMethod;
}

