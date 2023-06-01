package br.com.delivery.eats.restaurant.domain.core.entity;

import br.com.delivery.eats.common.domain.entity.AggregateRoot;
import br.com.delivery.eats.common.domain.exception.DomainException;
import br.com.delivery.eats.common.domain.valueobject.ProductId;
import br.com.delivery.eats.common.domain.valueobject.RestaurantId;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

//TODO
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
        super.setId(builder.id);
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

    public Restaurant validateRestaurant(Restaurant currentRestaurant) {
        validId(currentRestaurant);
        validRestaurant(currentRestaurant);
        validSubTotal();
        validQuantity(currentRestaurant);
        validateActiveProducts(currentRestaurant.getProducts());
        return this;
    }



    private void validateActiveProducts(List<Product> products) {
        for (Product product : products) {
            if (!product.getAvailable()) {
                throw new IllegalStateException("Produto inativo: " + product.getName());
            }
        }
    }

    private void validQuantity(Restaurant currentRestaurant) {
        Map<ProductId, Integer> quantityCurrent = getQuantity(this.products);
        Map<ProductId, Integer> quantityFromDb = getQuantity(currentRestaurant.getProducts());

        for (Map.Entry<ProductId, Integer> entry : quantityCurrent.entrySet()) {
            ProductId productId = entry.getKey();
            Integer quantityCurrentVal = entry.getValue();
            Integer quantityFromDbVal = quantityFromDb.get(productId);

            if (quantityFromDbVal != null && quantityCurrentVal > quantityFromDbVal) {
                throw new DomainException("Nao temos esta quantidade disponivel!");
            }
        }
    }

    private Map<ProductId, Integer> getQuantity(List<Product> products) {
        return products.stream().collect(Collectors.groupingBy(Product::getId, Collectors.summingInt(i -> i.getQuantity().getValue())));
    }

    private void validRestaurant(Restaurant currentRestaurant) {
        if(!currentRestaurant.getActive()){
            throw new DomainException("Restaurant is not active");
        }
    }

     void validSubTotal(){
        BigDecimal total = this.getProducts().stream()
                .map(product -> product.getPrice().getAmount().multiply(new BigDecimal(product.getQuantity().getValue())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        if(!total.equals(getSubTotal())){
            throw new DomainException("Sub total is invalid");
        }
    }

    BigDecimal getSubTotal(){
        return this.getProducts().stream().map(item -> item.getSubTotal().getAmount()).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    void validId(Restaurant currentRestaurant) {
      if(!currentRestaurant.getId().equals(this.getId())){
          throw new DomainException("Restaurant id error");
      }
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
