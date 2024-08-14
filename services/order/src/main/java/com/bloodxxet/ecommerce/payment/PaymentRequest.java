package com.bloodxxet.ecommerce.payment;

import com.bloodxxet.ecommerce.customer.CustomerResponse;
import com.bloodxxet.ecommerce.order.entity.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Long orderId,
        String orderReference,
        CustomerResponse customer
) {
}
