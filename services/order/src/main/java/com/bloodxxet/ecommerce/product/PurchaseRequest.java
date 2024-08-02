package com.bloodxxet.ecommerce.product;

import jakarta.validation.constraints.NotNull;

public record PurchaseRequest(

        @NotNull(message = "Product is mandatory")
        Long productId,

        @NotNull(message = "Quantity is mandatory")
        Integer quantity

) {
}
