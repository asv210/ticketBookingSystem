package com.darkSProject.ticketBooking.payment.dto;

import lombok.Builder;

@Builder
public record PaymentEventDTO(

        String eventId,

        String ticketId,

        String userId,

        Double amount,

        PaymentMethod paymentMethod,

        PaymentProvider paymentProvider,

        PaymentRequest paymentDetails


) {}
