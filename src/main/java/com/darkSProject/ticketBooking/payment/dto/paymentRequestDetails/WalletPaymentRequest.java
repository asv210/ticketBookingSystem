package com.darkSProject.ticketBooking.payment.dto.paymentRequestDetails;

import com.darkSProject.ticketBooking.payment.dto.PaymentRequest;
import com.darkSProject.ticketBooking.payment.dto.WalletProvider;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WalletPaymentRequest
        extends PaymentRequest {

    private WalletProvider walletProvider;

    private String walletId;
}
