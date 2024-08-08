package com.bloodxxet.ecommerce.order.service.impl;

import com.bloodxxet.ecommerce.exception.BusinessException;
import com.bloodxxet.ecommerce.customer.CustomerClient;
import com.bloodxxet.ecommerce.kafka.OrderConfirmation;
import com.bloodxxet.ecommerce.kafka.OrderProducer;
import com.bloodxxet.ecommerce.order.dto.OrderRequest;
import com.bloodxxet.ecommerce.order.dto.OrderResponse;
import com.bloodxxet.ecommerce.order.mapper.OrderMapper;
import com.bloodxxet.ecommerce.order.repository.OrderRepository;
import com.bloodxxet.ecommerce.order.service.OrderService;
import com.bloodxxet.ecommerce.orderline.dto.OrderLineRequest;
import com.bloodxxet.ecommerce.orderline.service.OrderLineService;
import com.bloodxxet.ecommerce.product.ProductClientRestTemplate;
import com.bloodxxet.ecommerce.product.ProductFeignClient;
import com.bloodxxet.ecommerce.product.PurchaseRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ProblemDetail;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final CustomerClient customerClient;
    private final ProductFeignClient productClient;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderLineService orderLineService;

    private final OrderProducer orderProducer;

    @Override
    public Long createOrder(OrderRequest request) {

        var customer = customerClient.getCustomerById(request.customerId())
                .orElseThrow(() -> new BusinessException("Cannot create order :: Cannot find customer with id" + request.customerId()));

        var purchasedProducts = productClient.purchaseProducts(request.purchasedProducts());

        var order = orderRepository.save(orderMapper.requestToOrder(request));

        for (PurchaseRequest purchaseRequest : request.purchasedProducts()) {
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )
            );
        }

        // todo Payment process

        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        request.reference(),
                        request.totalAmount(),
                        request.paymentMethod(),
                        customer,
                        purchasedProducts
                )
        );

        return order.getId();
    }

    @Override
    public List<OrderResponse> findAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(orderMapper::orderToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public OrderResponse getOrderById(Long id) {
        return orderRepository.findById(id)
                .map(orderMapper::orderToResponse)
                .orElseThrow(
                        () -> new EntityNotFoundException("Cannot find order with id" + id)
                );
    }
}
