package com.nisanth.ecommerce.product;

import com.nisanth.ecommerce.category.Category;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String description;
    private double availableQuantity;
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;


}
