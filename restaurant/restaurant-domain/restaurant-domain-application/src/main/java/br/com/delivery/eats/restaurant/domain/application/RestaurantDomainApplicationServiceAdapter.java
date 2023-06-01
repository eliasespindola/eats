package br.com.delivery.eats.restaurant.domain.application;

import br.com.delivery.eats.common.domain.events.DomainEventType;
import br.com.delivery.eats.common.domain.mapper.Mapper;
import br.com.delivery.eats.common.domain.valueobject.RestaurantId;
import br.com.delivery.eats.restaurant.domain.application.dto.ProductResponse;
import br.com.delivery.eats.restaurant.domain.application.dto.RestaurantRequest;
import br.com.delivery.eats.restaurant.domain.application.dto.RestaurantResponse;
import br.com.delivery.eats.restaurant.domain.application.ports.input.RestaurantDomainApplicationServicePort;
import br.com.delivery.eats.restaurant.domain.application.ports.output.RestaurantRepository;
import br.com.delivery.eats.restaurant.domain.core.RestaurantDomainPort;
import br.com.delivery.eats.restaurant.domain.core.entity.Product;
import br.com.delivery.eats.restaurant.domain.core.entity.Restaurant;
import br.com.delivery.eats.restaurant.domain.core.exception.RestaurantNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
public class RestaurantDomainApplicationServiceAdapter implements RestaurantDomainApplicationServicePort {

    private final RestaurantRepository repository;
    private final Mapper<List<Product>, List<ProductResponse>> productResponseMapper;
    private final RestaurantDomainPort restaurantDomainPort;

    public RestaurantDomainApplicationServiceAdapter(RestaurantRepository repository,
                                                     Mapper<List<Product>, List<ProductResponse>> productResponseMapper, RestaurantDomainPort restaurantDomainPort) {
        this.repository = repository;
        this.productResponseMapper = productResponseMapper;
        this.restaurantDomainPort = restaurantDomainPort;
    }

    @Override
    public RestaurantResponse trackRestaurant(RestaurantRequest trackOrderQuery) {
        Optional<Restaurant> possibleRestaurant = repository.findRestaurantById(new RestaurantId(trackOrderQuery.getRestaurantId()));
        if(possibleRestaurant.isEmpty()){
            log.info("Restaurant with id {} not found", trackOrderQuery.getRestaurantId().toString());
            throw new RestaurantNotFoundException("Restaurant with id:" + trackOrderQuery.getRestaurantId() +" not found");
        }
        Restaurant restaurant = possibleRestaurant.get();
        return RestaurantResponse.builder()
                .id(restaurant.getId().getValue())
                .name(restaurant.getName())
                .active(restaurant.getActive())
                .products(productResponseMapper.map(restaurant.getProducts()))
                .build();
    }

    @Override
    public Restaurant handleRestaurant(Restaurant restaurant, DomainEventType domainEventType) {
        Restaurant currentRestaurant = existRestaurant(restaurant.getId().getValue());
        Restaurant restaurantValid = currentRestaurant.substract(restaurantDomainPort.valid(restaurant, currentRestaurant).getProducts());
        return restaurantValid;
    }

    private Restaurant existRestaurant(UUID restaurantId) {
        Optional<Restaurant> possibleRestaurant = repository.findRestaurantById(new RestaurantId(restaurantId));
        if(possibleRestaurant.isEmpty()){
            throw new RestaurantNotFoundException("Restaurant with id " + restaurantId + "not found!");
        }
        return possibleRestaurant.get();
    }


}
