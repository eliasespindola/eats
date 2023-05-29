package br.com.delivery.eats.order.domain.messaging.mapper;


import br.com.delivery.eats.common.database.entity.event.DomainEventType;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.*;



@Component
class DomainEventMapperStrategy {

    @Bean
    Map<DomainEventType, Set<DomainEventMapper>> eventMapperStrategies(final DomainEventMapper orderMessagingCreateMapper) {
        final var strategies = new EnumMap<DomainEventType, LinkedHashSet<DomainEventMapper>>(DomainEventType.class);
        strategies.put(DomainEventType.ORDER_PENDING_EVENT, new LinkedHashSet<>(List.of(orderMessagingCreateMapper)));
        return Collections.unmodifiableMap(strategies);
    }

}
