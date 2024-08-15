package com.bloodxxet.ecommerce.notification;

import com.bloodxxet.ecommerce.kafka.order.OrderConfirmation;
import com.bloodxxet.ecommerce.kafka.payment.PaymentConfirmation;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Notification {

    @Id
    private String id;

    private NotificationType type;
    private LocalDateTime notificationDate;

    private OrderConfirmation orderConfirmation;
    private PaymentConfirmation paymentConfirmation;

}
