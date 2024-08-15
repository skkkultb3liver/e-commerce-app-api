package com.bloodxxet.ecommerce.kafka.order;

import java.math.BigDecimal;

public record Product(
        Long productId,
        String title,
        String description,
        Integer quantity,
        BigDecimal price
) {
}
