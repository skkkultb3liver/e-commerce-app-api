package com.bloodxxet.ecommerce.payment.service;

import com.bloodxxet.ecommerce.payment.dto.PaymentRequest;

public interface PaymentService {

    Long createPayment(PaymentRequest request);

}
