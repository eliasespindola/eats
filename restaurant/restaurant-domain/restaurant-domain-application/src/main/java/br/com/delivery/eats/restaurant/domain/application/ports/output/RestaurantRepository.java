package br.com.delivery.eats.restaurant.domain.application.ports.output;

import br.com.delivery.eats.common.domain.valueobject.RestaurantId;
import br.com.delivery.eats.restaurant.domain.core.entity.Restaurant;

import java.util.Optional;

public interface RestaurantRepository {
    Optional<Restaurant> findRestaurantById(final RestaurantId restaurantId);

    Restaurant save(final Restaurant restaurant);
}
