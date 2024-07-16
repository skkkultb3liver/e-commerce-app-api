package com.bloodxxet.ecommerce.customer.service.impl;

import com.bloodxxet.ecommerce.customer.dto.CustomerRequest;
import com.bloodxxet.ecommerce.customer.dto.CustomerResponse;
import com.bloodxxet.ecommerce.customer.entity.Customer;
import com.bloodxxet.ecommerce.exception.CustomerNotFoundException;
import com.bloodxxet.ecommerce.customer.mapper.CustomerMapper;
import com.bloodxxet.ecommerce.customer.repository.CustomerRepository;
import com.bloodxxet.ecommerce.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;
    private final CustomerMapper mapper;

    @Override
    public String createCustomer(CustomerRequest request) {

        var customer = repository.save(mapper.toCustomer(request));
        return customer.getId();
    }

    @Override
    public void updateCustomer(CustomerRequest request) {

        var updatedCustomer = repository.findById(request.id()).orElseThrow(
                () -> new CustomerNotFoundException(
                        String.format("Cannot update customer:: Cannot find customer with ID %s", request.id())
                )
        );

        mergeCustomer(updatedCustomer, request);
        repository.save(updatedCustomer);
    }

    @Override
    public List<CustomerResponse> findAllCustomers() {

        return repository.findAll()
                .stream()
                .map(mapper::fromCustomer)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerResponse getCustomerById(String id) {

        return mapper.fromCustomer(repository.findById(id).orElseThrow(
                () -> new CustomerNotFoundException(String.format("Cannot find customer with ID %s", id))
        ));
    }

    @Override
    public boolean isCustomerExist(String id) {

        return repository.findById(id).isPresent();
    }

    @Override
    @Transactional
    public void deleteCustomerById(String id) {
        repository.deleteById(id);
    }


    private void mergeCustomer(Customer customer, CustomerRequest request) {

        if (StringUtils.isNotBlank(request.firstName()) && !(request.firstName().equals(customer.getFirstName()))) {
            customer.setFirstName(request.firstName());
        }

        if (StringUtils.isNotBlank(request.lastName()) && !(request.lastName().equals(customer.getLastName()))) {
            customer.setFirstName(request.lastName());
        }

        if (StringUtils.isNotBlank(request.email()) && !(request.email().equals(customer.getEmail()))) {
            customer.setFirstName(request.email());
        }

        if (StringUtils.isNotBlank(request.phone()) && !(request.phone().equals(customer.getPhone()))) {
            customer.setFirstName(request.phone());
        }

        if ((request.address() != null) && !(request.address().equals(customer.getAddress()))) {
            customer.setAddress(request.address());
        }
    }

}
