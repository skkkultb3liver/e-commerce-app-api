package com.bloodxxet.ecommerce.payment.mapper;

import com.bloodxxet.ecommerce.payment.dto.PaymentRequest;
import com.bloodxxet.ecommerce.payment.dto.PaymentResponse;
import com.bloodxxet.ecommerce.payment.entity.Payment;
import org.springframework.stereotype.Service;

@Service
public class PaymentMapper {

    public Payment toPaymentEntity(PaymentRequest request) {

        if (request == null) return null;

        return Payment.builder()
                .paymentMethod(request.paymentMethod())
                .amount(request.amount())
                .orderId(request.orderId())
                .build();
    }

    public PaymentResponse toPaymentResponse(Payment payment) {

        if (payment == null) return null;

        return new PaymentResponse(
                payment.getId(),
                payment.getPaymentMethod(),
                payment.getAmount(),
                payment.getOrderId()
        );

    }
}
