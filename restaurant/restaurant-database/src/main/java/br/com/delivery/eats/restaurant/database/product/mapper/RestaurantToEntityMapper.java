package br.com.delivery.eats.restaurant.database.product.mapper;

import br.com.delivery.eats.common.domain.mapper.Mapper;
import br.com.delivery.eats.restaurant.database.product.entity.ProductEntity;
import br.com.delivery.eats.restaurant.database.restaurant.entity.RestaurantEntity;
import br.com.delivery.eats.restaurant.domain.core.entity.Product;
import br.com.delivery.eats.restaurant.domain.core.entity.Restaurant;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RestaurantToEntityMapper implements Mapper<Restaurant, RestaurantEntity> {

    private final  Mapper<List<Product>, List<ProductEntity>> productEntityMapper;

    public RestaurantToEntityMapper(Mapper<List<Product>, List<ProductEntity>> productEntityMapper) {
        this.productEntityMapper = productEntityMapper;
    }

    @Override
    public RestaurantEntity map(Restaurant restaurant) {
        return RestaurantEntity.builder()
                .id(restaurant.getId().getValue())
                .active(restaurant.getActive())
                .name(restaurant.getName())
                .items(productEntityMapper.map(restaurant.getProducts()))
                .build();
    }
}
