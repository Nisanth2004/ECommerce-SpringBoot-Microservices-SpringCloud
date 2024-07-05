package com.nisanth.ecommerce.customer;

public record CustomerResponse(
        // customer object response
        String id,
        String firstname,
        String lastname,
        String email
) {
}
