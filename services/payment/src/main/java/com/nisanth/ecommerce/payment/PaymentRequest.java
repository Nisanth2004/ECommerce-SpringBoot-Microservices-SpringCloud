package com.nisanth.ecommerce.payment;

import org.apache.kafka.common.protocol.types.Field;

import java.math.BigDecimal;

public record PaymentRequest(
        Integer id,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        Customer customer    // info about customer
) {
}
