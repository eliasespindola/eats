package br.com.delivery.eats.order.domain.core.exception;

import br.com.delivery.eats.common.domain.exception.DomainException;

public class OrderDomainNotFoundException extends DomainException {

    public OrderDomainNotFoundException(String message) {
        super(message);
    }

    public OrderDomainNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
