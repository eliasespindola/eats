package br.com.delivery.eats.order.domain.core.entity;

import br.com.delivery.eats.common.domain.entity.AggregateRoot;
import br.com.delivery.eats.common.domain.valueobject.*;
import br.com.delivery.eats.order.domain.core.valueobject.StreetAddress;
import br.com.delivery.eats.order.domain.core.valueobject.TrackingId;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class Order extends AggregateRoot<OrderId> {
    private final CustomerId customerId;
    private final RestaurantId restaurantId;
    private final StreetAddress deliveryAddress;
    private final Money price;
    private final List<OrderItem> items;

    private TrackingId trackingId;
    private OrderStatus orderStatus;
    private List<String> failureMessages;
}
