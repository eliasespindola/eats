package br.com.delivery.eats.common.domain.valueobject;

public class Quantity {
    private final Integer quantity;

    public Quantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
