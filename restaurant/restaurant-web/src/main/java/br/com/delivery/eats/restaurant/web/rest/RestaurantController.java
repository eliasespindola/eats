package br.com.delivery.eats.restaurant.web.rest;


import br.com.delivery.eats.restaurant.domain.application.dto.RestaurantRequest;
import br.com.delivery.eats.restaurant.domain.application.dto.RestaurantResponse;
import br.com.delivery.eats.restaurant.domain.application.ports.input.RestaurantDomainApplicationServicePort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(value = "/restaurants", produces = "application/vnd.api.v1+json")
public class RestaurantController {

    private final RestaurantDomainApplicationServicePort restaurantDomainApplicationServicePort;

    public RestaurantController(RestaurantDomainApplicationServicePort restaurantDomainApplicationServicePort) {
        this.restaurantDomainApplicationServicePort = restaurantDomainApplicationServicePort;
    }

    @GetMapping("/{restaurantId}")
    public ResponseEntity<RestaurantResponse> getRestaurantById(@PathVariable UUID restaurantId) {
        RestaurantResponse restaurantResponse = restaurantDomainApplicationServicePort
                .trackRestaurant(RestaurantRequest.builder()
                        .restaurantId(restaurantId)
                        .build());
        return  ResponseEntity.ok(restaurantResponse);
    }
}
