package com.nisanth.ecommerce.payment;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.validation.annotation.Validated;

@Validated
public record Customer(
        String id,
        @NotNull(message = "Firstname is required")
        String  firstname,

        @NotNull(message = "Lastname is Required")
        String lastname,

        @NotNull(message = "Email is Required")
        @Email(message = "The cutsomer is not correctly formatted")
        String email
) {

}
