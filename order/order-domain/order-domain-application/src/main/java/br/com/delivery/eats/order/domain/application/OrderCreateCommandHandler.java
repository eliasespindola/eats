package br.com.delivery.eats.order.domain.application;

import br.com.delivery.eats.common.domain.mapper.Mapper;
import br.com.delivery.eats.common.domain.valueobject.CustomerId;
import br.com.delivery.eats.order.domain.application.dto.create.CreateOrderRequest;
import br.com.delivery.eats.order.domain.application.dto.create.CreateOrderResponse;
import br.com.delivery.eats.order.domain.core.entity.Customer;
import br.com.delivery.eats.order.domain.core.entity.OrderItem;
import br.com.delivery.eats.order.domain.core.entity.Restaurant;
import br.com.delivery.eats.order.domain.core.valueobject.StreetAddress;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Slf4j
@Component
public class OrderCreateCommandHandler {
    private final Mapper<CreateOrderRequest,Restaurant> restaurantMapper;
    private final Mapper<CreateOrderRequest,StreetAddress> streetAddressMapper;
    private final Mapper<CreateOrderRequest,List<OrderItem>> orderItemMapper;

    public OrderCreateCommandHandler(Mapper<CreateOrderRequest, Restaurant> restaurantMapper,
                                     Mapper<CreateOrderRequest, StreetAddress> streetAddressMapper,
                                     Mapper<CreateOrderRequest, List<OrderItem>> orderItemMapper) {
        this.restaurantMapper = restaurantMapper;
        this.streetAddressMapper = streetAddressMapper;
        this.orderItemMapper = orderItemMapper;
    }


    public CreateOrderResponse createOrder(CreateOrderRequest createOrderCommand) {
        final Restaurant restaurant = restaurantMapper.map(createOrderCommand);
        final StreetAddress streetAddress = streetAddressMapper.map(createOrderCommand);
        final List<OrderItem> orderItems = orderItemMapper.map(createOrderCommand);
        checkCustomer(createOrderCommand.getCustomerId());
        return null;
    }

    private void checkCustomer(UUID customerId) {

    }
}
