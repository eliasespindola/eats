package br.com.delivery.eats.order.client.adapter;

import br.com.delivery.eats.common.domain.valueobject.RestaurantId;
import br.com.delivery.eats.order.client.RestaurantClient;
import br.com.delivery.eats.order.client.dto.RestaurantResponse;
import br.com.delivery.eats.order.client.mapper.ProductResponseToProduct;
import br.com.delivery.eats.order.domain.application.ports.output.RestaurantClientPort;
import br.com.delivery.eats.order.domain.core.entity.Restaurant;
import org.springframework.stereotype.Component;

@Component
public class RestaurantClientAdapter implements RestaurantClientPort {
    private final RestaurantClient restaurantClient;
    private final ProductResponseToProduct productResponseToProduct;

    public RestaurantClientAdapter(RestaurantClient restaurantClient, ProductResponseToProduct productResponseToProduct) {
        this.restaurantClient = restaurantClient;
        this.productResponseToProduct = productResponseToProduct;
    }

    @Override
    public Restaurant findById(Restaurant restaurant) {
        RestaurantResponse response = restaurantClient.getRestaurantById(restaurant.getId().getValue());
        return Restaurant.builder()
                .restaurantId(new RestaurantId(response.getId()))
                .active(response.getActive())
                .products(productResponseToProduct.map(response.getProducts()))
                .build();
    }
}
