package br.com.delivery.eats.restaurant.domain.core;

import br.com.delivery.eats.restaurant.domain.core.entity.Restaurant;
import br.com.delivery.eats.restaurant.domain.core.exception.RestaurantDomainException;

public class RestaurantDomainAdapter implements RestaurantDomainPort {


    @Override
    public Restaurant valid(Restaurant restaurant, Restaurant currentRestaurant) {
        return restaurant.validateRestaurant(currentRestaurant);
    }
}
