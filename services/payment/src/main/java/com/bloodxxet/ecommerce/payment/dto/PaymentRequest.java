package com.bloodxxet.ecommerce.payment.dto;

import com.bloodxxet.ecommerce.customer.Customer;
import com.bloodxxet.ecommerce.payment.entity.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        Long id,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Long orderId,
        String orderReference,
        Customer customer
) {
}
