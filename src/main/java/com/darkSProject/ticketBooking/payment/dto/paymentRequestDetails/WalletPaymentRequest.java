package com.darkSProject.ticketBooking.payment.dto.paymentRequestDetails;

import com.darkSProject.ticketBooking.payment.dto.PaymentRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WalletPaymentRequest
        extends PaymentRequest {

    private String walletProvider;

    private String walletId;
}
