package com.bloodxxet.ecommerce.product.dto;

import java.math.BigDecimal;

public record ProductPurchaseResponse(
        Long productId,
        String title,
        String description,
        BigDecimal price,
        Integer quantity
) {
}
