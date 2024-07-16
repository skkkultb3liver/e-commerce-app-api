package com.bloodxxet.ecommerce.customer.entity;

import lombok.*;
import org.springframework.validation.annotation.Validated;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Validated
public class Address {

    private String city;
    private String street;
    private String houseNumber;
    private String zipCode;
}
