package br.com.delivery.eats.common.domain.valueobject;

public class Quantity {
    private final Integer value;

    public Quantity(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
