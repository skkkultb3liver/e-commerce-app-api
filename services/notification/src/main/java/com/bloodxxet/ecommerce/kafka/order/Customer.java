package com.bloodxxet.ecommerce.kafka.order;

import org.springframework.validation.annotation.Validated;

public record Customer(
        String customerId,
        String firstName,
        String lastName,
        String email
) {
}
