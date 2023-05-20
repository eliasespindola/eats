package br.com.delivery.eats.restaurant.database.restaurant.mapper;

import br.com.delivery.eats.common.domain.mapper.Mapper;
import br.com.delivery.eats.common.domain.valueobject.RestaurantId;
import br.com.delivery.eats.restaurant.database.product.entity.ProductEntity;
import br.com.delivery.eats.restaurant.database.restaurant.entity.RestaurantEntity;
import br.com.delivery.eats.restaurant.domain.core.entity.Product;
import br.com.delivery.eats.restaurant.domain.core.entity.Restaurant;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RestaurantEntityToRestaurantMapper implements Mapper<RestaurantEntity, Restaurant> {

    private final Mapper<List<ProductEntity>, List<Product>> productMapper;

    public RestaurantEntityToRestaurantMapper(Mapper<List<ProductEntity>, List<Product>> productMapper) {
        this.productMapper = productMapper;
    }

    @Override
    public Restaurant map(RestaurantEntity restaurantEntity) {
        return Restaurant.Builder.builder()
                .id(new RestaurantId(restaurantEntity.getId()))
                .name(restaurantEntity.getName())
                .active(restaurantEntity.getActive())
                .products(productMapper.map(restaurantEntity.getItems()))
                .build();
    }
}
