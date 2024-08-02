package com.bloodxxet.ecommerce.order.service;

import com.bloodxxet.ecommerce.order.dto.OrderRequest;
import org.springframework.http.ProblemDetail;

public interface OrderService {
    ProblemDetail createOrder(OrderRequest request);
}
