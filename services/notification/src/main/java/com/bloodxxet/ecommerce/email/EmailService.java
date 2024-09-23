package com.bloodxxet.ecommerce.email;

import com.bloodxxet.ecommerce.kafka.order.Product;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.bloodxxet.ecommerce.email.EmailTemplate.ORDER_CONFIRMATION;
import static com.bloodxxet.ecommerce.email.EmailTemplate.PAYMENT_NOTIFICATION;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.springframework.mail.javamail.MimeMessageHelper.MULTIPART_MODE_RELATED;

@Service
@RequiredArgsConstructor
public class EmailService {

    private static final Logger log = LoggerFactory.getLogger(EmailService.class);
    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    public void sendPaymentSuccessEmail(
            String destinationEmail,
            String customerName,
            BigDecimal amount,
            String orderReference
    ) throws MessagingException {

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(
                message,
                MULTIPART_MODE_RELATED,
                UTF_8.name()
        );

        messageHelper.setFrom("contact@bloodxxet.com");

        final String templateName = PAYMENT_NOTIFICATION.getTemplate();

        Map<String, Object> variables = new HashMap<>();
        variables.put("customerName", customerName);
        variables.put("amount", amount);
        variables.put("orderReference", orderReference);

        Context context = new Context();
        context.setVariables(variables);

        messageHelper.setSubject(PAYMENT_NOTIFICATION.getSubject());

        try {
            String html = templateEngine.process(templateName, context);
            messageHelper.setText(html, true);

            messageHelper.setTo(destinationEmail);

            mailSender.send(message);
            log.info("Email sent to {} with template {}", destinationEmail, templateName);
        } catch (MessagingException e) {
            log.warn("Cannot send email to {}", destinationEmail);
        }
    }

    public void sendOrderConfirmationEmail(
            String destinationEmail,
            String customerName,
            BigDecimal amount,
            String orderReference,
            List<Product> products
    ) throws MessagingException {

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(
                message,
                MULTIPART_MODE_RELATED,
                UTF_8.name()
        );

        messageHelper.setFrom("contact@bloodxxet.com");

        final String templateName = ORDER_CONFIRMATION.getTemplate();

        Map<String, Object> variables = new HashMap<>();
        variables.put("customerName", customerName);
        variables.put("totalAmount", amount);
        variables.put("orderReference", orderReference);
        variables.put("products", products);

        Context context = new Context();
        context.setVariables(variables);

        messageHelper.setSubject(ORDER_CONFIRMATION.getSubject());

        try {
            String html = templateEngine.process(templateName, context);
            messageHelper.setText(html, true);

            messageHelper.setTo(destinationEmail);

            mailSender.send(message);
            log.info("Email sent to {} with template {}", destinationEmail, templateName);
        } catch (MessagingException e) {
            log.warn("Cannot send email to {}", destinationEmail);
        }
    }

}
