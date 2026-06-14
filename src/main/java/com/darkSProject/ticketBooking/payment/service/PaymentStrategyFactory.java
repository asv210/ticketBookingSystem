package com.darkSProject.ticketBooking.payment.service;

import com.darkSProject.ticketBooking.common.exception.ErrorCode;
import com.darkSProject.ticketBooking.common.exception.UnsupportedPaymentMethodException;
import com.darkSProject.ticketBooking.payment.dto.PaymentMethod;
import com.darkSProject.ticketBooking.payment.service.stretegy.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentStrategyFactory {

    private final CardPaymentStrategy card;
    private final UpiPaymentStrategy upi;
    private final WalletPaymentStrategy wallet;
    private final NetBankingPaymentStrategy netBanking;

    public PaymentStrategy getStrategy(
            PaymentMethod method
    ) {
        if (method == null) {
            throw new UnsupportedPaymentMethodException(
                    "Payment method cannot be null",
                    ErrorCode.UNSUPPORTED_PAYMENT_METHOD
            );
        }

        return switch (method) {
            case CARD -> card;
            case UPI -> upi;
            case WALLET -> wallet;
            case NET_BANKING -> netBanking;
            default -> throw new UnsupportedPaymentMethodException(
                    "Payment method not supported",
                    ErrorCode.UNSUPPORTED_PAYMENT_METHOD
            );
        };
    }
}
