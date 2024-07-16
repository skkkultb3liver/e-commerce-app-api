package com.bloodxxet.ecommerce.customer.controller;

import com.bloodxxet.ecommerce.customer.dto.CustomerRequest;
import com.bloodxxet.ecommerce.customer.dto.CustomerResponse;
import com.bloodxxet.ecommerce.customer.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/create")
    public ResponseEntity<String> createCustomerHandler(
            @Valid @RequestBody CustomerRequest request
    ) {
        return ResponseEntity.ok(customerService.createCustomer(request));
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateCustomerHandler(
            @Valid @RequestBody CustomerRequest request
    ) {
        customerService.updateCustomer(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/")
    public ResponseEntity<List<CustomerResponse>> getAllCustomersHandler() {
        return ResponseEntity.ok(customerService.findAllCustomers());
    }

    @GetMapping("/{customer_id}")
    public ResponseEntity<CustomerResponse> getCustomerHandler(
            @PathVariable("customer_id") String customerId
    ) {
        return ResponseEntity.ok(customerService.getCustomerById(customerId));
    }

    @GetMapping("/exist/{customer_id}")
    public ResponseEntity<Boolean> isCustomerExistHandler(
            @PathVariable("customer_id") String customerId
    ) {
        return ResponseEntity.ok(customerService.isCustomerExist(customerId));
    }

    @DeleteMapping("/{customer_id}")
    public ResponseEntity<String> deleteCustomerHandler(
            @PathVariable("customer_id") String customerId
    ) {
        customerService.deleteCustomerById(customerId);
        return ResponseEntity.accepted().build();
    }
}
