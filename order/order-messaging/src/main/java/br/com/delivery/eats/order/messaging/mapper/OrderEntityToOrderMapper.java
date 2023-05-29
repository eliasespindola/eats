package br.com.delivery.eats.order.messaging.mapper;

import br.com.delivery.eats.common.domain.mapper.Mapper;
import br.com.delivery.eats.common.domain.valueobject.CustomerId;
import br.com.delivery.eats.common.domain.valueobject.Money;
import br.com.delivery.eats.common.domain.valueobject.OrderId;
import br.com.delivery.eats.common.domain.valueobject.RestaurantId;
import br.com.delivery.eats.order.database.entity.order.entity.OrderAddressEntity;
import br.com.delivery.eats.order.database.entity.order.entity.OrderEntity;
import br.com.delivery.eats.order.database.entity.order.entity.OrderItemEntity;
import br.com.delivery.eats.order.domain.core.entity.Order;
import br.com.delivery.eats.order.domain.core.entity.OrderItem;
import br.com.delivery.eats.order.domain.core.valueobject.StreetAddress;
import br.com.delivery.eats.order.domain.core.valueobject.TrackingId;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderEntityToOrderMapper implements Mapper<OrderEntity, Order> {

    private final  Mapper<List<OrderItemEntity>, List<OrderItem>> orderItems;
    private final Mapper<OrderAddressEntity, StreetAddress> streetAddressMapper;

    public OrderEntityToOrderMapper(Mapper<List<OrderItemEntity>, List<OrderItem>> orderItems,
                                    Mapper<OrderAddressEntity, StreetAddress> streetAddressMapper) {
        this.orderItems = orderItems;
        this.streetAddressMapper = streetAddressMapper;
    }

    @Override
    public Order map(OrderEntity orderEntity) {
        return Order.builder()
                .orderId(new OrderId(orderEntity.getId()))
                .customerId(new CustomerId(orderEntity.getCustomerId()))
                .restaurantId(new RestaurantId(orderEntity.getRestaurantId()))
                .price(new Money(orderEntity.getPrice()))
                .failureMessages(List.of(orderEntity.getFailureMessages()))
                .trackingId(new TrackingId(orderEntity.getTrackingId()))
                .orderStatus(orderEntity.getOrderStatus())
                .items(orderItems.map(orderEntity.getItems()))
                .deliveryAddress(streetAddressMapper.map(orderEntity.getAddress()))
                .build();
    }
}
