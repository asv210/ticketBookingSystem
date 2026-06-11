package com.darkSProject.ticketBooking.payment.dto;

import lombok.Builder;

@Builder
public record PaymentResult(

        boolean success,

        String transactionId,

        String failureReason
) {

    public static PaymentResult success(
            String txnId
    ) {

        return PaymentResult.builder()
                .success(true)
                .transactionId(txnId)
                .build();
    }

    public static PaymentResult failed(
            String reason
    ) {

        return PaymentResult.builder()
                .success(false)
                .failureReason(reason)
                .build();
    }
}
