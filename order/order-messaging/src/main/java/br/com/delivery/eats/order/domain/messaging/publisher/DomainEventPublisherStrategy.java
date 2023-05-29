package br.com.delivery.eats.order.domain.messaging.publisher;

import br.com.delivery.eats.common.database.entity.event.DomainEventType;
import br.com.delivery.eats.common.messaging.event.publisher.DomainEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
class DomainEventPublisherStrategy {
    @Bean
    Map<DomainEventType, Set<DomainEventPublisher>> eventPublisherStrategies(final DomainEventPublisher outboundPublisher) {
        final var strategies = new EnumMap<DomainEventType, LinkedHashSet<DomainEventPublisher>>(DomainEventType.class);
        strategies.put(DomainEventType.ORDER_PENDING_EVENT, new LinkedHashSet<>(List.of(outboundPublisher)));
        return Collections.unmodifiableMap(strategies);
    }
}
