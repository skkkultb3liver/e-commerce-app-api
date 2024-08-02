package com.bloodxxet.ecommerce.orderline.dto;

public record OrderLineRequest(
        Long id,
        Long orderId,
        Long productId,
        Integer quantity) {
}
