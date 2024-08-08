package com.bloodxxet.ecommerce.order.dto;

import com.bloodxxet.ecommerce.order.entity.PaymentMethod;

import java.math.BigDecimal;

public record OrderResponse(
        Long id,
        String reference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        String customerId
) {
}
