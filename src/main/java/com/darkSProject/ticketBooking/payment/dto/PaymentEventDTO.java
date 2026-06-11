package com.darkSProject.ticketBooking.payment.dto;

import com.darkSProject.ticketBooking.payment.dto.paymentRequestDetails.*;
import lombok.Builder;

@Builder
public record PaymentEventDTO(

        String eventId,

        String ticketId,

        String userId,

        Double amount,

        PaymentMethod paymentMethod,

        String currency,

        CardPaymentRequest cardDetails,

        UpiPaymentRequest upiDetails,

        WalletPaymentRequest walletDetails,

        NetBankingPaymentRequest netBankingDetails,

        PaypalPaymentRequest paypalDetails

) {}
