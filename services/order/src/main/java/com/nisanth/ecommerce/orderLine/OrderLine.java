package com.nisanth.ecommerce.orderLine;

import com.nisanth.ecommerce.order.Order;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Setter
@Entity
public class OrderLine {

    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne
    @JoinColumn(name="order_id")
    private Order order;

    private Integer productId;
    private double quantity;


}
