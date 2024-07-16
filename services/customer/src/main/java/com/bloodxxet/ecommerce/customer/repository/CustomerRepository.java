package com.bloodxxet.ecommerce.customer.repository;

import com.bloodxxet.ecommerce.customer.entity.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {
}
