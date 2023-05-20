package br.com.delivery.eats.order.domain.application.ports.output;

import br.com.delivery.eats.order.domain.core.entity.Restaurant;

import java.util.Optional;

public interface RestaurantRepository {
    Optional<Restaurant> findById(Restaurant restaurant);

    void save(Restaurant restaurant);
}
