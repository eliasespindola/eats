package br.com.delivery.eats.common.messaging.event.publisher;

import br.com.delivery.eats.common.database.entity.event.DomainEventEntity;
import br.com.delivery.eats.common.messaging.domain.MessageBody;

import java.util.Map;

public interface DomainEventPublisher<T extends DomainEventEntity> {

    void publish(final MessageBody<?> event, Map<String, Object> headers);

}
