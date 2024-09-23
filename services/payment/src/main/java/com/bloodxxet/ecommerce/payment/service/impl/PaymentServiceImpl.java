package com.bloodxxet.ecommerce.payment.service.impl;

import com.bloodxxet.ecommerce.notification.NotificationProducer;
import com.bloodxxet.ecommerce.notification.PaymentNotification;
import com.bloodxxet.ecommerce.payment.dto.PaymentRequest;
import com.bloodxxet.ecommerce.payment.mapper.PaymentMapper;
import com.bloodxxet.ecommerce.payment.repository.PaymentRepository;
import com.bloodxxet.ecommerce.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository repository;
    private final PaymentMapper mapper;
    private final NotificationProducer notificationProducer;

    @Override
    public Long createPayment(PaymentRequest request) {

        log.info("Create payment request: {}", request);

        var payment = repository.save(mapper.toPaymentEntity(request));

        notificationProducer.sendPaymentNotification(

                new PaymentNotification(
                        request.orderReference(),
                        request.amount(),
                        request.paymentMethod(),
                        request.customer().firstName(),
                        request.customer().lastName(),
                        request.customer().email()
                )
        );

        return payment.getId();
    }
}
