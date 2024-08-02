package com.bloodxxet.ecommerce.orderline.mapper;

import com.bloodxxet.ecommerce.order.entity.Order;
import com.bloodxxet.ecommerce.orderline.dto.OrderLineRequest;
import com.bloodxxet.ecommerce.orderline.entity.OrderLine;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderLineMapper {

    public OrderLine fromRequestToOrderLine(OrderLineRequest request) {

        if (request == null) return null;

        OrderLine orderLine = OrderLine.builder()
                .id(request.id())
                .quantity(request.quantity())
                .order(
                        Order.builder()
                                .id(request.orderId())
                                .build()
                )
                .productId(request.productId())
                .build();

        return orderLine;
    }
}
