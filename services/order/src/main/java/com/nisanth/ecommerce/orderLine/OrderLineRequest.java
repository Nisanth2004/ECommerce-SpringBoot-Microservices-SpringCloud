package com.nisanth.ecommerce.orderLine;

public record OrderLineRequest(

         Integer id,
        Integer orderId,
        Integer productId,

         double quantity
) {
}
