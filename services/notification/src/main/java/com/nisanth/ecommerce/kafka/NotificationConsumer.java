package com.nisanth.ecommerce.kafka;

import com.nisanth.ecommerce.email.EmailService;
import com.nisanth.ecommerce.kafka.order.OrderConfirmation;
import com.nisanth.ecommerce.kafka.payment.NotificationRepository;
import com.nisanth.ecommerce.kafka.payment.PaymentConfirmation;
import com.nisanth.ecommerce.notification.Notification;
import com.nisanth.ecommerce.notification.NotificationType;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.nisanth.ecommerce.notification.NotificationType.*;
import static java.lang.String.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {
    private final NotificationRepository repository;
    private final EmailService emailService;


    // send payment Confirmation email
    @KafkaListener(topics="payment-topic")
    public void consumePaymentSuccessNotification(PaymentConfirmation paymentConfirmation) throws MessagingException {
        log.info(format("Consuming the message from payment-topic Topic::%s",paymentConfirmation));

        // save the notification
        repository.save(
                Notification.builder()
                        .type(PAYMENT_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .paymentConfirmation(paymentConfirmation)

                        .build()
        );

        // send email
        var customerName=paymentConfirmation.customerFirstname()+" "+ paymentConfirmation.customerLastname();
        emailService.sendPaymentSuccessEmail(
                paymentConfirmation.customerEmail(),
                customerName,
                paymentConfirmation.amount(),
                paymentConfirmation.orderReference()
        );
    }






    // send order Confirmation email

    @KafkaListener(topics="order-topic")
    public void consumeOrderConfirmationNotification(OrderConfirmation orderConfirmation) throws MessagingException {
        log.info(format("Consuming the message from order-topic Topic::%s",orderConfirmation));

        // save the notification
        repository.save(
                Notification.builder()
                        .type(ORDER_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .orderConfirmation(orderConfirmation)

                        .build()
        );



        var customerName=orderConfirmation.customer().firstname()+" "+ orderConfirmation.customer().lastname();
        emailService.sendOrderConfirmationEmail(
                orderConfirmation.customer().email(),
                customerName,
                orderConfirmation.totalAmount(),
                orderConfirmation.orderReference(),
                orderConfirmation.products()
        );
    }
    }

