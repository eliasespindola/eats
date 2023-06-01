package br.com.delivery.eats.restaurant.domain.application.ports.input;

import br.com.delivery.eats.common.domain.events.DomainEventType;
import br.com.delivery.eats.restaurant.domain.application.dto.RestaurantRequest;
import br.com.delivery.eats.restaurant.domain.application.dto.RestaurantResponse;
import br.com.delivery.eats.restaurant.domain.core.entity.Restaurant;
import jakarta.validation.Valid;

import java.util.UUID;

public interface RestaurantDomainApplicationServicePort {

    RestaurantResponse trackRestaurant(@Valid RestaurantRequest trackOrderQuery);

    Restaurant handleRestaurant(UUID restaurantId, DomainEventType domainEventType);
}
