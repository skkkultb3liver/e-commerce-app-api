package com.bloodxxet.ecommerce.customer.dto;

import com.bloodxxet.ecommerce.customer.entity.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest (
        String id,

        @NotNull(message = "Firstname is required")
        String firstName,

        @NotNull(message = "Lastname is required")
        String lastName,

        @NotNull(message = "Email is required")
        @Email(message = "Invalid email address")
        String email,

        @NotNull(message = "Mobile phone is required")
        String phone,

        Address address
) {
}
