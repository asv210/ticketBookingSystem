package com.darkSProject.ticketBooking.payment.service;

import com.darkSProject.ticketBooking.payment.dto.PaymentEventDTO;
import com.darkSProject.ticketBooking.payment.dto.PaymentResultEventDTO;
import com.darkSProject.ticketBooking.payment.dto.PaymentStatus;
import com.darkSProject.ticketBooking.payment.entity.PaymentTransaction;
import com.darkSProject.ticketBooking.payment.repository.PaymentTransactionRepository;
import com.darkSProject.ticketBooking.payment.service.stretegy.PaymentStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentStrategyFactory factory;
    private final PaymentTransactionRepository paymentTransactionRepository;

    @Transactional
    public PaymentResultEventDTO process(
            PaymentEventDTO event
    ) {

        PaymentStrategy strategy =
                factory.getStrategy(
                        event.paymentMethod()
                );

        PaymentResultEventDTO result = strategy.pay(event);

        PaymentTransaction transaction = PaymentTransaction.builder()
                .ticketId(event.ticketId())
                .userId(event.userId())
                .amount(event.amount())
                .paymentMethod(event.paymentMethod())
                .status(result.success() ? PaymentStatus.SUCCESS : PaymentStatus.FAILED)
                .gatewayReference(result.eventId())
                .createdAt(LocalDateTime.now())
                .build();

        paymentTransactionRepository.save(transaction);

        return result;
    }
}
