package com.nisanth.ecommerce.product;

import jakarta.validation.constraints.NotNull;

public record ProductPurchaseRequest(
        @NotNull(message = "Product is mandatory")
        Integer productId,

        @NotNull(message = "Quantity is manadatory")
        double quantity
) {
}
