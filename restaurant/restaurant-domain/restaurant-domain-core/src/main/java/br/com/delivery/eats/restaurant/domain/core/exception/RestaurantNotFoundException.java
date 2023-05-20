package br.com.delivery.eats.restaurant.domain.core.exception;

import br.com.delivery.eats.common.domain.exception.DomainException;

public class RestaurantNotFoundException  extends DomainException {
    public RestaurantNotFoundException(String message) {
        super(message);
    }

    public RestaurantNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
