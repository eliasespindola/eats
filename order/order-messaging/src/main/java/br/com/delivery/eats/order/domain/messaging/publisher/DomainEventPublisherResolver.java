package br.com.delivery.eats.order.domain.messaging.publisher;

import br.com.delivery.eats.common.database.entity.event.DomainEventType;
import br.com.delivery.eats.common.messaging.event.publisher.DomainEventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class DomainEventPublisherResolver {
    private final Map<DomainEventType, Set<DomainEventPublisher>> eventPublisherStrategies;

    public Set<DomainEventPublisher> resolve(final DomainEventType domainEvent) {
        return eventPublisherStrategies.getOrDefault(domainEvent, Collections.emptySet());
    }
}
