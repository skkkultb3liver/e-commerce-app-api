package com.bloodxxet.ecommerce.order.dto;

import com.bloodxxet.ecommerce.order.entity.PaymentMethod;
import com.bloodxxet.ecommerce.product.PurchaseRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;

public record OrderRequest(
        Long id,

        String reference,

        @Positive(message = "Order total amount should be positive")
        Integer totalAmount,

        @NotNull(message = "Payment method should be precised")
        PaymentMethod paymentMethod,

        @NotNull(message = "Customer id should be present")
        @NotEmpty(message = "Customer id should be present")
        @NotBlank(message = "Customer id should be present")
        String customerId,

        @NotEmpty(message = "You should purchase at least one product")
        List<PurchaseRequest> purchasedProducts
) {
}
