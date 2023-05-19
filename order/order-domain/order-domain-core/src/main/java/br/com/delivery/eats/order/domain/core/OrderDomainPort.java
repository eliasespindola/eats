package br.com.delivery.eats.order.domain.core;

import br.com.delivery.eats.order.domain.core.entity.Order;
import br.com.delivery.eats.order.domain.core.entity.Restaurant;

public interface OrderDomainPort {
    void validateAndInitiateOrder(Order order, Restaurant restaurant);

}
