package br.com.delivery.eats.restaurant.domain.core.entity;

import br.com.delivery.eats.common.domain.entity.BaseEntity;
import br.com.delivery.eats.common.domain.exception.DomainException;
import br.com.delivery.eats.common.domain.valueobject.Money;
import br.com.delivery.eats.common.domain.valueobject.ProductId;
import br.com.delivery.eats.common.domain.valueobject.Quantity;

import java.util.List;

public class Product extends BaseEntity<ProductId> {

    private String name;
    private Quantity quantity;
    private Money price;

    private Boolean available;

    private Money subTotal;

    public String getName() {
        return name;
    }

    public Quantity getQuantity() {
        return quantity;
    }

    public Money getPrice() {
        return price;
    }

    public Boolean getAvailable() {
        return available;
    }

    public Money getSubTotal() {
        return subTotal;
    }

    private Product(Builder builder) {
        super.setId(builder.id);
        name = builder.name;
        quantity = builder.quantity;
        price = builder.price;
        available = builder.available;
        subTotal = builder.subTotal;
    }

    public void validQuantity(List<Product> products) {
        for(int i=0; i < products.size(); i++){
            if(products.get(i).getId() == this.getId()){
                if(this.getQuantity().getValue() > products.get(i).getQuantity().getValue()){
                    throw new DomainException(" Nao temos quantidade o suficiente");
                }
            }
        }
    }


    public static final class Builder {
        private ProductId id;
        private String name;
        private Quantity quantity;
        private Money price;
        private Boolean available;

        private Money subTotal;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder id(ProductId val) {
            id = val;
            return this;
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder subTotal(Money val) {
            subTotal = val;
            return this;
        }

        public Builder quantity(Quantity val) {
            quantity = val;
            return this;
        }

        public Builder price(Money val) {
            price = val;
            return this;
        }

        public Builder available(Boolean val) {
            available = val;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }
}
