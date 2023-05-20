package br.com.delivery.eats.restaurant.database.product.entity;


import br.com.delivery.eats.restaurant.database.restaurant.entity.RestaurantEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "product")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity {
    @Id
    private UUID id;
    private String name;
    private Integer quantity;
    private BigDecimal price;
    private Boolean available;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "RESTAURANT_ID")
    private RestaurantEntity restaurant;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductEntity that = (ProductEntity) o;
        return id.equals(that.id) && name.equals(that.name) && Objects.equals(quantity, that.quantity) && price.equals(that.price) && Objects.equals(available, that.available);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, quantity, price, available);
    }
}
