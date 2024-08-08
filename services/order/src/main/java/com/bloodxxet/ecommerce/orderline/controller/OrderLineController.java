package com.bloodxxet.ecommerce.orderline.controller;

import com.bloodxxet.ecommerce.orderline.dto.OrderLineResponse;
import com.bloodxxet.ecommerce.orderline.service.OrderLineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order_line")
@RequiredArgsConstructor
public class OrderLineController {

    private final OrderLineService orderLineService;

    @GetMapping("/order/{order_id}")
    public ResponseEntity<List<OrderLineResponse>> findByOrderId(
            @PathVariable("order_id") Long orderId
    ) {
        return ResponseEntity.ok(orderLineService.findAllByOrderId(orderId));
    }
}
