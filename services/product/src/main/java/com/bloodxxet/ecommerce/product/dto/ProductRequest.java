package com.bloodxxet.ecommerce.product.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductRequest(
        @NotNull(message = "Product title required")
        String title,

        @NotNull(message = "Product description required")
        String description,

        @Positive(message = "Product quantity should be positive")
        Integer quantity,

        @Positive(message = "Product price should be positive")
        BigDecimal price,

        @NotNull(message = "Product category required")
        Long categoryId
) {
}
