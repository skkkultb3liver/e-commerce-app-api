package com.bloodxxet.ecommerce.orderline.repository;

import com.bloodxxet.ecommerce.orderline.dto.OrderLineResponse;
import com.bloodxxet.ecommerce.orderline.entity.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderLineRepository extends JpaRepository<OrderLine, Long> {

    List<OrderLine> findAllByOrderId(Long orderId);
}
