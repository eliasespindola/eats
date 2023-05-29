package br.com.delivery.eats.order.domain.messaging.resolver;


import br.com.delivery.eats.common.database.entity.event.DomainEventType;
import br.com.delivery.eats.common.messaging.domain.MessageBody;

public interface MessageBuilderResolver<T> {

    boolean shouldResolve(final DomainEventType domainEventType);

    MessageBody<?> build(final T source);

}
