package br.com.delivery.eats.restaurant.messaging.config;

import br.com.delivery.eats.common.domain.events.DomainEventType;
import br.com.delivery.eats.restaurant.messaging.domain.dto.OrderMessageEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MessageConverter;

import java.util.EnumMap;
import java.util.Map;


@Configuration
class MessageWrapperConfiguration {

    @Bean
    public MessageConverter messageWrapperConverter(final ObjectMapper objectMapper,
        final Map<DomainEventType, Class<?>> domainEventTypeClassMap) {
        return new MessageWrapperConverter(objectMapper, domainEventTypeClassMap);
    }

    @Bean
    public Map<DomainEventType, Class<?>> domainEventTypeClassMap() {
        final var map = new EnumMap<DomainEventType, Class<?>>(DomainEventType.class);
        map.put(DomainEventType.ORDER_PENDING_EVENT, OrderMessageEvent.class);

        return map;
    }

}
