package com.nisanth.ecommerce.kafka.order;

import com.nisanth.ecommerce.kafka.payment.PaymentMethod;
import org.apache.kafka.common.protocol.types.Field;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        Customer customer,
        List<Product> products
) {
}
