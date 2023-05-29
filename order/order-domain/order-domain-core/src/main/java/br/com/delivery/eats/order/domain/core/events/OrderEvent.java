package br.com.delivery.eats.order.domain.core.events;

import br.com.delivery.eats.common.domain.events.DomainEvent;
import br.com.delivery.eats.common.domain.events.DomainEventType;

import java.time.ZonedDateTime;
import java.util.UUID;

public abstract class OrderEvent<T> extends DomainEvent<T> {
    private final ZonedDateTime createdAt;
    protected OrderEvent(UUID id, DomainEventType type, ZonedDateTime createdAt) {
        super(id, type);
        this.createdAt = createdAt;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }
}
