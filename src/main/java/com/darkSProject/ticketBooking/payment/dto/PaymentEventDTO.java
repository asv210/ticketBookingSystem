package com.darkSProject.ticketBooking.payment.dto;

import lombok.Builder;

@Builder
public record PaymentEventDTO(

        String ticketId,

        String userId,

        Double amount
) {}
