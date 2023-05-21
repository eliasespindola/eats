package br.com.delivery.eats.common.database.entity.event.publisher;


import br.com.delivery.eats.common.database.entity.event.DomainEventEntity;

public interface DomainEventPublisher<T extends DomainEventEntity> {

    void publish(T t);
}
