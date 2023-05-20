package br.com.delivery.eats.restaurant.domain.application;

import br.com.delivery.eats.common.domain.mapper.Mapper;
import br.com.delivery.eats.common.domain.valueobject.RestaurantId;
import br.com.delivery.eats.restaurant.domain.application.dto.ProductResponse;
import br.com.delivery.eats.restaurant.domain.application.dto.RestaurantRequest;
import br.com.delivery.eats.restaurant.domain.application.dto.RestaurantResponse;
import br.com.delivery.eats.restaurant.domain.application.ports.input.RestaurantDomainApplicationServicePort;
import br.com.delivery.eats.restaurant.domain.application.ports.output.RestaurantRepository;
import br.com.delivery.eats.restaurant.domain.core.entity.Product;
import br.com.delivery.eats.restaurant.domain.core.entity.Restaurant;
import br.com.delivery.eats.restaurant.domain.core.exception.RestaurantNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class RestaurantDomainApplicationServiceAdapter implements RestaurantDomainApplicationServicePort {

    private final RestaurantRepository repository;
    private final Mapper<List<Product>, List<ProductResponse>> productResponseMapper;

    public RestaurantDomainApplicationServiceAdapter(RestaurantRepository repository,
                                                     Mapper<List<Product>, List<ProductResponse>> productResponseMapper) {
        this.repository = repository;
        this.productResponseMapper = productResponseMapper;
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
                .restaurantId(restaurant.getId().getValue())
                .restaurantName(restaurant.getName())
                .restaurantActive(restaurant.getActive())
                .products(productResponseMapper.map(restaurant.getProducts()))
                .build();
    }
}
