package com.bloodxxet.ecommerce.order.service.impl;

import com.bloodxxet.ecommerce.exception.BusinessException;
import com.bloodxxet.ecommerce.customer.CustomerClient;
import com.bloodxxet.ecommerce.order.dto.OrderRequest;
import com.bloodxxet.ecommerce.order.mapper.OrderMapper;
import com.bloodxxet.ecommerce.order.repository.OrderRepository;
import com.bloodxxet.ecommerce.order.service.OrderService;
import com.bloodxxet.ecommerce.orderline.dto.OrderLineRequest;
import com.bloodxxet.ecommerce.orderline.service.OrderLineService;
import com.bloodxxet.ecommerce.product.ProductClientRestTemplate;
import com.bloodxxet.ecommerce.product.ProductFeignClient;
import com.bloodxxet.ecommerce.product.PurchaseRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ProblemDetail;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final CustomerClient customerClient;
    private final ProductFeignClient productClient;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderLineService orderLineService;

    @Override
    public ProblemDetail createOrder(OrderRequest request) {

        var customer = customerClient.getCustomerById(request.customerId())
                .orElseThrow(() -> new BusinessException("Cannot create order :: Cannot find customer with id" + request.customerId()));

        productClient.purchaseProducts(request.purchasedProducts());

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
        // todo Send order confirmation (notification service)
        return null;
    }
}
