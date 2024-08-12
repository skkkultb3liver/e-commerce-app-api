package com.bloodxxet.ecommerce.payment.dto;

import com.bloodxxet.ecommerce.payment.entity.PaymentMethod;

import java.math.BigDecimal;

public record PaymentResponse(
        Long paymentId,
        PaymentMethod paymentMethod,
        BigDecimal amount,
        Long orderId) {
}
