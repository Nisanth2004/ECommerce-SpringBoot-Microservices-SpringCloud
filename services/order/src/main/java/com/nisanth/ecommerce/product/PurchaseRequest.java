package com.nisanth.ecommerce.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PurchaseRequest(
        @NotNull(message = "Product is manadatory")
        Integer productId,
        @Positive(message = "Quantity is manadatory")
        double quantity


) {
}
