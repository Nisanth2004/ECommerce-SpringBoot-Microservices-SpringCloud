package com.nisanth.ecommerce.notification;

import com.nisanth.ecommerce.payment.PaymentMethod;
import org.apache.kafka.common.protocol.types.Field;

import java.math.BigDecimal;

public record PaymentNotificationRequest(
        String orderReference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerFirstame,
        String customerLastame,
        String customerEmail
) {
}
