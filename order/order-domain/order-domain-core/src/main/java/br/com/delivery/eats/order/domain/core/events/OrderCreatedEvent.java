package br.com.delivery.eats.order.domain.core.events;

import br.com.delivery.eats.common.domain.events.DomainEventType;
import br.com.delivery.eats.order.domain.core.entity.Order;

import java.time.ZonedDateTime;
import java.util.UUID;

public class OrderCreatedEvent extends OrderEvent<Order> {
    private final Order order;

    public OrderCreatedEvent(Order order) {
        super(UUID.randomUUID(), DomainEventType.ORDER_PENDING_EVENT, ZonedDateTime.now());
        this.order = order;
    }

    @Override
    public Order getSource() {
        return order;
    }
}
