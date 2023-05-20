package br.com.delivery.eats.restaurant.domain.core;

import br.com.delivery.eats.restaurant.domain.core.entity.Restaurant;
import br.com.delivery.eats.restaurant.domain.core.exception.RestaurantDomainException;

public class RestaurantDomainAdapter implements RestaurantDomainPort {


    @Override
    public void validateAndInitiateRestaurant(Restaurant restaurant) {
        validateRestaurant(restaurant);
    }

    private void validateRestaurant(final Restaurant restaurant) {
        if(!restaurant.getActive()){
            throw new RestaurantDomainException("Restaurant with id " + restaurant.getId().getValue() + " is currently not active!");
        }
    }
}
