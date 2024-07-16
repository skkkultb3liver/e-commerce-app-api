package com.bloodxxet.ecommerce.customer.service;

import com.bloodxxet.ecommerce.customer.dto.CustomerRequest;
import com.bloodxxet.ecommerce.customer.dto.CustomerResponse;

import java.util.List;

public interface CustomerService {

    String createCustomer(CustomerRequest request);

    void updateCustomer(CustomerRequest request);

    List<CustomerResponse> findAllCustomers();

    CustomerResponse getCustomerById(String id);

    boolean isCustomerExist(String id);

    void deleteCustomerById(String id);
}
