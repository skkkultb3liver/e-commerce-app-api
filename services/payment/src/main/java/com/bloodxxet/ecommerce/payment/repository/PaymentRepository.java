package com.bloodxxet.ecommerce.payment.repository;

import com.bloodxxet.ecommerce.payment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
