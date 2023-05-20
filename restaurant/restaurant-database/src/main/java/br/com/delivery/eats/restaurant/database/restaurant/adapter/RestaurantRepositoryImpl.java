package br.com.delivery.eats.restaurant.database.restaurant.adapter;

import br.com.delivery.eats.common.domain.valueobject.RestaurantId;
import br.com.delivery.eats.restaurant.domain.application.ports.output.RestaurantRepository;
import br.com.delivery.eats.restaurant.domain.core.entity.Restaurant;

import java.util.Optional;

public class RestaurantRepositoryImpl implements RestaurantRepository {
    @Override
    public Optional<Restaurant> findRestaurantById(RestaurantId restaurantId) {
        return Optional.empty();
    }

    @Override
    public Restaurant save(Restaurant restaurant) {
        return null;
    }
}
