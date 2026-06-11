package com.darkSProject.ticketBooking.payment.dto.paymentRequestDetails;

import com.darkSProject.ticketBooking.payment.dto.PaymentRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NetBankingPaymentRequest
        extends PaymentRequest {

    private String bankName;

    private String accountNumber;
}
