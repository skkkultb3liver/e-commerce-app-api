package com.bloodxxet.ecommerce.customer.dto;

import com.bloodxxet.ecommerce.customer.entity.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerResponse(
        String id,

        String firstName,

        String lastName,

        String email,

        String phone,

        Address address
) {
}
