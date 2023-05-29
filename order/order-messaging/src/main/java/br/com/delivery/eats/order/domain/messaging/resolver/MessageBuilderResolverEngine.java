package br.com.delivery.eats.order.domain.messaging.resolver;



import br.com.uber.eats.order.database.order.entity.events.DomainEventEntity;
import br.com.uber.eats.order.messaging.domain.MessageBody;
import br.com.uber.eats.order.messaging.exception.NotFoundDomainException;

import java.util.Collections;
import java.util.List;

import static java.lang.String.format;

public abstract class MessageBuilderResolverEngine<T extends DomainEventEntity<?>> {

    private final List<MessageBuilderResolver<T>> resolvers;

    protected MessageBuilderResolverEngine(final List<MessageBuilderResolver<T>> resolvers) {
        this.resolvers = Collections.unmodifiableList(resolvers);
    }

    public MessageBody<?> resolveEvent(final T domainEvent) {
        final var domainEventType = domainEvent.getType();
        return resolvers.stream().filter(resolver -> resolver.shouldResolve(domainEventType))
            .findFirst()
            .map(resolver -> resolver.build(domainEvent))
            .orElseThrow(() -> new NotFoundDomainException(format("Domain event builder resolver not found. Unknown event [%s]", domainEventType)));
    }

}
