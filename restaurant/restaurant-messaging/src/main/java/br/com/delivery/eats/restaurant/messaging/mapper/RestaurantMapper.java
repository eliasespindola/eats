package br.com.delivery.eats.restaurant.messaging.mapper;

import br.com.delivery.eats.common.domain.mapper.Mapper;
import br.com.delivery.eats.common.domain.valueobject.RestaurantId;
import br.com.delivery.eats.restaurant.domain.core.entity.Product;
import br.com.delivery.eats.restaurant.domain.core.entity.Restaurant;
import br.com.delivery.eats.restaurant.messaging.domain.dto.ItensMessageEvent;
import br.com.delivery.eats.restaurant.messaging.domain.dto.OrderMessageEvent;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RestaurantMapper implements Mapper<OrderMessageEvent, Restaurant> {
    private final Mapper<List<ItensMessageEvent>, List<Product>> productMapper;

    public RestaurantMapper(Mapper<List<ItensMessageEvent>, List<Product>> productMapper) {
        this.productMapper = productMapper;
    }

    @Override
    public Restaurant map(OrderMessageEvent orderMessageEvent) {
        return Restaurant.Builder.builder()
                .id(new RestaurantId(orderMessageEvent.getRestaurantId()))
                .products(productMapper.map(orderMessageEvent.getItems()))
                .build();
    }
}
