package br.com.delivery.eats.order.domain.application.ports.output;

import br.com.delivery.eats.order.domain.core.entity.Restaurant;

public interface RestaurantClientPort {
   Restaurant findById(Restaurant restaurant);
}
