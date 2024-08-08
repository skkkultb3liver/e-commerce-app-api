package com.bloodxxet.ecommerce.order.mapper;

import com.bloodxxet.ecommerce.order.dto.OrderRequest;
import com.bloodxxet.ecommerce.order.dto.OrderResponse;
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
                .totalAmount(request.totalAmount())
                .paymentMethod(request.paymentMethod())
                .build();

        return order;
    }

    public OrderResponse orderToResponse(final Order order) {

        if (order == null) return null;

        return new OrderResponse(
                order.getId(),
                order.getReference(),
                order.getTotalAmount(),
                order.getPaymentMethod(),
                order.getCustomerId()
        );

    }

}
