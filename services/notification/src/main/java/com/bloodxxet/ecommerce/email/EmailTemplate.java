package com.bloodxxet.ecommerce.email;

import lombok.Getter;

@Getter
public enum EmailTemplate {

    PAYMENT_NOTIFICATION("payment-notification.html", "Payment successfully processed"),
    ORDER_CONFIRMATION("order-confirmation.html", "Order confirmation");

    private final String template;
    private final String subject;

    EmailTemplate(String template, String subject) {
        this.template = template;
        this.subject = subject;
    }
}
