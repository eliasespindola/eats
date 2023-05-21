package br.com.delivery.eats.order.client.exception;

import br.com.delivery.eats.common.domain.exception.DomainException;

public class GenericBusinessException extends DomainException {

    public GenericBusinessException(String message) {
        super("Generic error: " + message);
    }

}
