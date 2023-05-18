package br.com.delivery.eats.order.domain.application.mapper.create;

import br.com.delivery.eats.common.domain.mapper.Mapper;
import br.com.delivery.eats.common.domain.valueobject.Money;
import br.com.delivery.eats.common.domain.valueobject.ProductId;
import br.com.delivery.eats.common.domain.valueobject.RestaurantId;
import br.com.delivery.eats.order.domain.application.dto.create.CreateOrderRequest;
import br.com.delivery.eats.order.domain.core.entity.Product;
import br.com.delivery.eats.order.domain.core.entity.Restaurant;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CreateOrderRequestRestaurantToRestaurantMapper implements Mapper<CreateOrderRequest, Restaurant> {

    @Override
    public Restaurant map(CreateOrderRequest createOrderRequest) {
        final List<Product> products = createOrderRequest.getItems().stream().map(item -> new Product(new ProductId(item.getProductId()))).collect(Collectors.toList());
        return Restaurant.builder()
                .restaurantId(new RestaurantId(createOrderRequest.getRestaurantId()))
                .products(products)
                .build();
    }
}
