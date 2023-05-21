package br.com.delivery.eats.order.client.exception;

import br.com.delivery.eats.common.domain.exception.DomainException;

public class RestaurantClientException extends DomainException {

    public RestaurantClientException(final String message) {
        super(message);
    }
}
