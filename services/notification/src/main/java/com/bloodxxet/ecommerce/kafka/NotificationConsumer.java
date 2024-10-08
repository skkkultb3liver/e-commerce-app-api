package com.bloodxxet.ecommerce.kafka;

import com.bloodxxet.ecommerce.email.EmailService;
import com.bloodxxet.ecommerce.kafka.order.OrderConfirmation;
import com.bloodxxet.ecommerce.kafka.payment.PaymentNotification;
import com.bloodxxet.ecommerce.notification.Notification;
import com.bloodxxet.ecommerce.notification.NotificationRepository;
import com.bloodxxet.ecommerce.notification.NotificationType;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {

    private final NotificationRepository repository;
    private final EmailService emailService;

    @KafkaListener(topics = "payment-topic")
    public void consumePaymentNotification(
            PaymentNotification paymentNotification
    ) throws MessagingException {
        log.info("payment-topic :: Consuming payment confirmation: <{}>", paymentNotification);

        repository.save(
                Notification.builder()
                        .type(NotificationType.PAYMENT_NOTIFICATION)
                        .notificationDate(LocalDateTime.now())
                        .paymentNotification(paymentNotification)
                        .build()
        );

        var customerName = paymentNotification.customerFirstName() + " " + paymentNotification.customerLastName();
        emailService.sendPaymentSuccessEmail(
                paymentNotification.customerEmail(),
                customerName,
                paymentNotification.amount(),
                paymentNotification.orderReference()
        );
    }

    @KafkaListener(topics = "order-topic")
    public void consumeOrderConfirmationNotification(
            OrderConfirmation orderConfirmation
    ) throws MessagingException {
        log.info("order-topic :: Consuming order confirmation: <{}>", orderConfirmation);

        repository.save(
                Notification.builder()
                        .type(NotificationType.ORDER_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .orderConfirmation(orderConfirmation)
                        .build()
        );

        var customerName = orderConfirmation.customer().firstName() + " " +
        orderConfirmation.customer().lastName();

        emailService.sendOrderConfirmationEmail(
                orderConfirmation.customer().email(),
                customerName,
                orderConfirmation.totalAmount(),
                orderConfirmation.orderReference(),
                orderConfirmation.products()
        );
    }
}
