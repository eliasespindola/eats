package br.com.delivery.eats.restaurant.domain.application.ports.input;

import br.com.delivery.eats.restaurant.domain.application.dto.RestaurantRequest;
import br.com.delivery.eats.restaurant.domain.application.dto.RestaurantResponse;
import jakarta.validation.Valid;

public interface RestaurantDomainApplicationServicePort {

    RestaurantResponse trackRestaurant(@Valid RestaurantRequest trackOrderQuery);
}
