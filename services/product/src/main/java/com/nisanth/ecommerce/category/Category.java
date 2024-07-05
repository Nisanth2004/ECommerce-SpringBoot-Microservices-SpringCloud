package com.nisanth.ecommerce.category;

import com.nisanth.ecommerce.product.Product;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Getter
@Setter
public class Category {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String description;

    @OneToMany(mappedBy = "category",cascade = CascadeType.REMOVE)
    private List<Product> products;
}
