package com.darkSProject.ticketBooking.payment.dto;

import com.darkSProject.ticketBooking.payment.dto.paymentRequestDetails.CardPaymentRequest;
import com.darkSProject.ticketBooking.payment.dto.paymentRequestDetails.NetBankingPaymentRequest;
import com.darkSProject.ticketBooking.payment.dto.paymentRequestDetails.UpiPaymentRequest;
import com.darkSProject.ticketBooking.payment.dto.paymentRequestDetails.WalletPaymentRequest;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "@type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = CardPaymentRequest.class, name = "CARD"),
        @JsonSubTypes.Type(value = UpiPaymentRequest.class, name = "UPI"),
        @JsonSubTypes.Type(value = WalletPaymentRequest.class, name = "WALLET"),
        @JsonSubTypes.Type(value = NetBankingPaymentRequest.class, name = "NET_BANKING")
})
public abstract class PaymentRequest {

    private String ticketId;

    private String userId;

    private Double amount;

    private String currency;

    private PaymentMethod paymentMethod;

    private PaymentProvider paymentProvider;
}

