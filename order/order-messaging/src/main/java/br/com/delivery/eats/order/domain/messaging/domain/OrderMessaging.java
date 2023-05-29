package br.com.delivery.eats.order.domain.messaging.domain;


import br.com.delivery.eats.common.domain.valueobject.CustomerId;
import br.com.delivery.eats.common.domain.valueobject.Money;
import br.com.delivery.eats.common.domain.valueobject.OrderStatus;
import br.com.delivery.eats.common.domain.valueobject.RestaurantId;
import br.com.delivery.eats.order.domain.core.entity.OrderItem;
import br.com.delivery.eats.order.domain.core.valueobject.StreetAddress;
import br.com.delivery.eats.order.domain.core.valueobject.TrackingId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Getter
@ToString
@NoArgsConstructor(force = true, access = PRIVATE)
public class OrderMessaging {
    private final CustomerId customerId;
    private final RestaurantId restaurantId;
    private final StreetAddress deliveryAddress;
    private final Money price;
    private final List<OrderItem> items;

    private TrackingId trackingId;
    private OrderStatus orderStatus;

    public OrderMessaging(CustomerId customerId, RestaurantId restaurantId, StreetAddress deliveryAddress, Money price, List<OrderItem> items) {
        this.customerId = customerId;
        this.restaurantId = restaurantId;
        this.deliveryAddress = deliveryAddress;
        this.price = price;
        this.items = items;
    }

    private OrderMessaging(Builder builder) {
        customerId = builder.customerId;
        restaurantId = builder.restaurantId;
        deliveryAddress = builder.deliveryAddress;
        price = builder.price;
        items = builder.items;
        trackingId = builder.trackingId;
        orderStatus = builder.orderStatus;
    }


    public static final class Builder {
        private CustomerId customerId;
        private RestaurantId restaurantId;
        private StreetAddress deliveryAddress;
        private Money price;
        private List<OrderItem> items;
        private TrackingId trackingId;
        private OrderStatus orderStatus;

        private Builder() {
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder customerId(CustomerId val) {
            customerId = val;
            return this;
        }

        public Builder restaurantId(RestaurantId val) {
            restaurantId = val;
            return this;
        }

        public Builder deliveryAddress(StreetAddress val) {
            deliveryAddress = val;
            return this;
        }

        public Builder price(Money val) {
            price = val;
            return this;
        }

        public Builder items(List<OrderItem> val) {
            items = val;
            return this;
        }

        public Builder trackingId(TrackingId val) {
            trackingId = val;
            return this;
        }

        public Builder orderStatus(OrderStatus val) {
            orderStatus = val;
            return this;
        }

        public OrderMessaging build() {
            return new OrderMessaging(this);
        }
    }
}
