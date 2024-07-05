package com.nisanth.ecommerce.order;

import com.nisanth.ecommerce.product.PurchaseRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public record OrderRequest(
        Integer id,
        String reference,

        @Positive(message = "Order amount should be positive")
        BigDecimal amount,

        @NotNull(message = "Payment method should not be precised")
        PaymentMethod paymentMethod,
        @NotNull(message = "Customer should be prsent")
        @NotEmpty(message = "Customer should be prsent")
        @NotBlank(message = "Customer should be prsent")
        String customerId,

        @NotEmpty(message = "You should atleast purchase one product")
        List<PurchaseRequest> products
) {
}
