package com.darkSProject.ticketBooking.payment.dto.paymentRequestDetails;

import com.darkSProject.ticketBooking.payment.dto.PaymentRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardPaymentRequest
        extends PaymentRequest {

    private String cardNumber;

    private String cardHolderName;

    private String expiryMonth;

    private String expiryYear;

    private String cvv;
}
