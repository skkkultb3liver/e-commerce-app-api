package com.bloodxxet.ecommerce.order.controller;

import com.bloodxxet.ecommerce.order.dto.OrderRequest;
import com.bloodxxet.ecommerce.order.dto.OrderResponse;
import com.bloodxxet.ecommerce.order.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<Long> createOrderHandler(
            @RequestBody @Valid final OrderRequest request
    ) {
        return ResponseEntity.ok(orderService.createOrder(request));
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> findAllOrdersHandler() {
        return ResponseEntity.ok(orderService.findAllOrders());
    }

    @GetMapping("/{order_id}")
    public ResponseEntity<OrderResponse> getOrderByIdHandler(
            @PathVariable("order_id") @Valid final Long orderId
    ) {
        return ResponseEntity.ok(orderService.getOrderById(orderId));
    }
}
