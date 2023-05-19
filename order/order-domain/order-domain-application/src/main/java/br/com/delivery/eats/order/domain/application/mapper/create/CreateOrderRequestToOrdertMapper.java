package br.com.delivery.eats.order.domain.application.mapper.create;

import br.com.delivery.eats.common.domain.mapper.Mapper;
import br.com.delivery.eats.common.domain.valueobject.CustomerId;
import br.com.delivery.eats.common.domain.valueobject.Money;
import br.com.delivery.eats.common.domain.valueobject.RestaurantId;
import br.com.delivery.eats.order.domain.application.dto.create.CreateOrderRequest;
import br.com.delivery.eats.order.domain.core.entity.Order;
import org.springframework.stereotype.Component;

@Component
public class CreateOrderRequestToOrdertMapper implements Mapper<CreateOrderRequest, Order> {

    private final CreateOrderRequestToStreetAddressMapper streetAddressMapper;
    private final CreateOrderRequestToOrderItemMapper itemMapper;

    public CreateOrderRequestToOrdertMapper(CreateOrderRequestToStreetAddressMapper streetAddressMapper,
                                            CreateOrderRequestToOrderItemMapper itemMapper) {
        this.streetAddressMapper = streetAddressMapper;
        this.itemMapper = itemMapper;
    }

    @Override
    public Order map(CreateOrderRequest createOrderRequest) {
        return Order.builder()
                .restaurantId(new RestaurantId(createOrderRequest.getRestaurantId()))
                .customerId(new CustomerId(createOrderRequest.getCustomerId()))
                .deliveryAddress(streetAddressMapper.map(createOrderRequest))
                .price(new Money(createOrderRequest.getPrice()))
                .items(itemMapper.map(createOrderRequest))
                .build();
    }
}
