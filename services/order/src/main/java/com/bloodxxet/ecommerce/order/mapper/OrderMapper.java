package com.bloodxxet.ecommerce.order.mapper;

import com.bloodxxet.ecommerce.order.dto.OrderRequest;
import com.bloodxxet.ecommerce.order.entity.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderMapper {

    public Order requestToOrder(final OrderRequest request) {

        if (request == null) return null;

        Order order = Order.builder()
                .id(request.id())
                .customerId(request.customerId())
                .reference(request.reference())
                .totalQuantity(request.totalAmount())
                .paymentMethod(request.paymentMethod())
                .build();

        return order;
    }

}
