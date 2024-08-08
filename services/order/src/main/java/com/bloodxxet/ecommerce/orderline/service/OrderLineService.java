package com.bloodxxet.ecommerce.orderline.service;

import com.bloodxxet.ecommerce.orderline.dto.OrderLineRequest;
import com.bloodxxet.ecommerce.orderline.dto.OrderLineResponse;
import com.bloodxxet.ecommerce.orderline.mapper.OrderLineMapper;
import com.bloodxxet.ecommerce.orderline.repository.OrderLineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderLineService {

    private final OrderLineRepository repository;
    private final OrderLineMapper mapper;

    public Long saveOrderLine(OrderLineRequest orderLineRequest) {
        var orderLine = mapper.fromRequestToOrderLine(orderLineRequest);
        return repository.save(orderLine).getId();
    }


    public List<OrderLineResponse> findAllByOrderId(Long orderId) {
        return repository.findAllByOrderId(orderId)
                .stream()
                .map(mapper::fromEntityToResponse)
                .collect(Collectors.toList());
    }
}
