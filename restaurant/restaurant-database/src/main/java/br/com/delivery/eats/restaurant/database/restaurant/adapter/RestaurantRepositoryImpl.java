package br.com.delivery.eats.restaurant.database.restaurant.adapter;

import br.com.delivery.eats.common.domain.mapper.Mapper;
import br.com.delivery.eats.common.domain.valueobject.RestaurantId;
import br.com.delivery.eats.restaurant.database.restaurant.entity.RestaurantEntity;
import br.com.delivery.eats.restaurant.database.restaurant.repository.RestaurantJpaRepository;
import br.com.delivery.eats.restaurant.domain.application.ports.output.RestaurantRepository;
import br.com.delivery.eats.restaurant.domain.core.entity.Restaurant;

import java.util.Optional;

public class RestaurantRepositoryImpl implements RestaurantRepository {

    private final RestaurantJpaRepository repository;
    private final Mapper<RestaurantEntity, Restaurant>  restaurantEntityToRestaurantMapper;
    private final Mapper<Restaurant, RestaurantEntity> restaurantToRestaurantEntityMapper;

    public RestaurantRepositoryImpl(RestaurantJpaRepository repository,
                                    Mapper<RestaurantEntity, Restaurant> restaurantEntityToRestaurantMapper,
                                    Mapper<Restaurant, RestaurantEntity> restaurantToRestaurantEntityMapper) {
        this.repository = repository;
        this.restaurantEntityToRestaurantMapper = restaurantEntityToRestaurantMapper;
        this.restaurantToRestaurantEntityMapper = restaurantToRestaurantEntityMapper;
    }


    @Override
    public Optional<Restaurant> findRestaurantById(RestaurantId restaurantId) {
        return repository.findById(restaurantId.getValue()).map(restaurantEntityToRestaurantMapper::map);
    }

    @Override
    public Restaurant save(Restaurant restaurant) {
        RestaurantEntity entity = repository.save(restaurantToRestaurantEntityMapper.map(restaurant));
        return restaurantEntityToRestaurantMapper.map(entity);
    }
}
