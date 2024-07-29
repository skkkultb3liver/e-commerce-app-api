package com.bloodxxet.ecommerce.product.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductResponse(
        Long id,
        String title,
        String description,
        Integer quantity,
        BigDecimal price,
        Long categoryId,
        String categoryName,
        String categoryDescription,
        String categorySlug
) {
}
