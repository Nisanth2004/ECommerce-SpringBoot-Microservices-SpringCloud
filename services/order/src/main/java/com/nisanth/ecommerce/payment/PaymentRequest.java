package com.nisanth.ecommerce.payment;

import com.nisanth.ecommerce.customer.CustomerResponse;
import com.nisanth.ecommerce.order.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerResponse customer    // info about customer
) {
}
