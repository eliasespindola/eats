package br.com.delivery.eats.order.domain.messaging.resolver;


import java.util.Map;

public interface DomainEventPublisher {

    void publish(final MessageBody<?> event, Map<String, Object> headers);

}
