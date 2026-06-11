package com.darkSProject.ticketBooking.payment.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record PaymentResponseDTO(

        String paymentId,

        PaymentStatus status,

        String transactionId,

        String gatewayReferenceId,

        String message,

        LocalDateTime timestamp

) {}
