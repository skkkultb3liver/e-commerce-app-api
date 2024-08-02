package com.bloodxxet.ecommerce.orderline.repository;

import com.bloodxxet.ecommerce.orderline.entity.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderLineRepository extends JpaRepository<OrderLine, Long> {
}
