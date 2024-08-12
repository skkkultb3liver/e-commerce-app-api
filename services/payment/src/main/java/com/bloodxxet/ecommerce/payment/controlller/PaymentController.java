package com.bloodxxet.ecommerce.payment.controlller;

import com.bloodxxet.ecommerce.payment.dto.PaymentRequest;
import com.bloodxxet.ecommerce.payment.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/create")
    public ResponseEntity<Long> createPaymentHandler(
            @RequestBody(required = true) @Valid final PaymentRequest request
    ) {
        return ResponseEntity.ok(paymentService.createPayment(request));
    }

}
