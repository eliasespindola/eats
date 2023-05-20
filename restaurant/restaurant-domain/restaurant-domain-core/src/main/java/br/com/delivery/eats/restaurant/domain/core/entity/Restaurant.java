package br.com.delivery.eats.restaurant.domain.core.entity;

import br.com.delivery.eats.common.domain.entity.AggregateRoot;
import br.com.delivery.eats.common.domain.valueobject.RestaurantId;

import java.util.List;
import java.util.UUID;

public class Restaurant extends AggregateRoot<RestaurantId> {
    private String name;
    private Boolean active;
    private List<Product> products;

    public Restaurant(RestaurantId id) {
        super.setId(id);
    }

    public Restaurant(RestaurantId id, String name, Boolean active, List<Product> products) {
        super.setId(id);
        this.name = name;
        this.active = active;
        this.products = products;
    }

    private Restaurant(Builder builder) {
        id = builder.id;
        name = builder.name;
        active = builder.active;
        products = builder.products;
    }


    public String getName() {
        return name;
    }

    public Boolean getActive() {
        return active;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void initializeOrder() {
        setId(new RestaurantId(UUID.randomUUID()));
    }


    public static final class Builder {
        private RestaurantId id;
        private String name;
        private Boolean active;
        private List<Product> products;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder id(RestaurantId val) {
            id = val;
            return this;
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder active(Boolean val) {
            active = val;
            return this;
        }

        public Builder products(List<Product> val) {
            products = val;
            return this;
        }

        public Restaurant build() {
            return new Restaurant(this);
        }
    }
}
