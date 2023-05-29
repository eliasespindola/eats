package br.com.delivery.eats.common.domain.events;

import java.util.UUID;

public abstract class DomainEvent <T>{
    private final UUID id;
    private final DomainEventType type;

    protected DomainEvent(UUID id, DomainEventType type) {
        this.id = id;
        this.type = type;
    }

    public UUID getId() {
        return id;
    }

    public DomainEventType getType() {
        return type;
    }

    public abstract T getSource();
}
