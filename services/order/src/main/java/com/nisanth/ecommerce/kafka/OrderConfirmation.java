package com.nisanth.ecommerce.kafka;

import com.nisanth.ecommerce.customer.CustomerResponse;
import com.nisanth.ecommerce.order.PaymentMethod;
import com.nisanth.ecommerce.product.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

// send data to notification service
public record OrderConfirmation(

        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products
) {
}
