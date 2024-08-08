package com.bloodxxet.ecommerce.order.service;

import com.bloodxxet.ecommerce.order.dto.OrderRequest;
import com.bloodxxet.ecommerce.order.dto.OrderResponse;
import org.springframework.http.ProblemDetail;

import java.util.List;

public interface OrderService {

    Long createOrder(OrderRequest request);

    List<OrderResponse> findAllOrders();

    OrderResponse getOrderById(Long id);

}
