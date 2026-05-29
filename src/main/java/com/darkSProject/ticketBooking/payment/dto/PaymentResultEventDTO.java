package com.darkSProject.ticketBooking.payment.dto;

import lombok.Builder;

@Builder
public record PaymentResultEventDTO(
        String ticketId,

        boolean success,

        String eventId,

        String userId

) {
}
