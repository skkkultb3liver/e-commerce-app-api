package com.bloodxxet.ecommerce.product.dto;

import jakarta.validation.constraints.NotNull;

public record ProductPurchaseRequest(

        @NotNull(message = "Product id is mandatory")
        Long productId,

        @NotNull(message = "Product quantity is mandatory")
        Integer quantity
) {
}
