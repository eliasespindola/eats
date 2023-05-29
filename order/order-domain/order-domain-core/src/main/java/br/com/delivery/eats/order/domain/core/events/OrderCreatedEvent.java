package br.com.delivery.eats.order.domain.core.events;

import br.com.delivery.eats.common.domain.events.DomainEventType;
import br.com.delivery.eats.order.domain.core.entity.Order;

import java.time.ZonedDateTime;
import java.util.UUID;


public class OrderCreatedEvent extends OrderEvent<Order> {
    private final Order order;

    private OrderCreatedEvent(Builder builder) {
        super(UUID.randomUUID(), DomainEventType.ORDER_PENDING_EVENT, ZonedDateTime.now());
        order = builder.order;
    }

    @Override
    public Order getSource() {
        return order;
    }


    public static final class Builder {
        private Order order;

        private Builder() {
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder withOrder(Order val) {
            order = val;
            return this;
        }

        public OrderCreatedEvent build() {
            return new OrderCreatedEvent(this);
        }
    }
}
