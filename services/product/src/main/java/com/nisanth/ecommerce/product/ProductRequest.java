package com.nisanth.ecommerce.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductRequest(
        Integer id,

         @NotNull(message = "Product name is required")
         String name,
        @NotNull(message = "Product description is required")
        String description,
        @Positive(message = "Available Quantity should be Positive")
        double availableQuantity,
        @Positive(message = "Price should be Positive")
        BigDecimal price,

        @NotNull(message = "Price Category is required")
        Integer categoryId) {
}
