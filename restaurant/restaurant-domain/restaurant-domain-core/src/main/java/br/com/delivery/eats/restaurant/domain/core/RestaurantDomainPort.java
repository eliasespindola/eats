package br.com.delivery.eats.restaurant.domain.core;

import br.com.delivery.eats.restaurant.domain.core.entity.Restaurant;

public interface RestaurantDomainPort {
    Restaurant valid(Restaurant restaurant ,Restaurant currentRestaurant);

}
