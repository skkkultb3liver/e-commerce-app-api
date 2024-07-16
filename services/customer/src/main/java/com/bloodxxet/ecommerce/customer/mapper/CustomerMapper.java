package com.bloodxxet.ecommerce.customer.mapper;

import com.bloodxxet.ecommerce.customer.dto.CustomerRequest;
import com.bloodxxet.ecommerce.customer.dto.CustomerResponse;
import com.bloodxxet.ecommerce.customer.entity.Customer;
import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {

    public Customer toCustomer(CustomerRequest request) {

        if (request == null) return null;

        return Customer.builder()
                .id(request.id())
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .phone(request.phone())
                .address(request.address())
                .build();
    }

    public CustomerResponse fromCustomer(Customer customer) {

        if (customer == null) return null;

        return new CustomerResponse(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getPhone(),
                customer.getAddress()
        );
    }
}
