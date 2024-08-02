package com.bloodxxet.ecommerce.product;

import java.math.BigDecimal;

public record PurchaseResponse(
        Long productId,
        String title,
        String description,
        BigDecimal price,
        Integer quantity
)
{

}
