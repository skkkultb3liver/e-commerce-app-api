package com.bloodxxet.ecommerce.kafka;


import com.bloodxxet.ecommerce.customer.CustomerResponse;
import com.bloodxxet.ecommerce.order.entity.PaymentMethod;
import com.bloodxxet.ecommerce.product.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> purchaseResponses
) {
}
