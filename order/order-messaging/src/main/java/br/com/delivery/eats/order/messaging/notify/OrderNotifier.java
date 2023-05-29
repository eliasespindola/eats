package br.com.delivery.eats.order.messaging.notify;

import br.com.delivery.eats.common.messaging.domain.MessageBody;
import br.com.delivery.eats.common.messaging.event.listener.MessageNotifier;
import br.com.delivery.eats.common.messaging.event.publisher.DomainEventPublisher;
import br.com.delivery.eats.order.domain.core.events.OrderCreatedEvent;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.UUID;

@Component
public class OrderNotifier implements MessageNotifier<OrderCreatedEvent> {

    private final DomainEventPublisher domainEventPublisher;

    public OrderNotifier(DomainEventPublisher domainEventPublisher) {
        this.domainEventPublisher = domainEventPublisher;
    }

    @Override
    public void notify(OrderCreatedEvent source) {
        HashMap<String, Object> headers = new HashMap<>();
        headers.put("correlation-id", UUID.randomUUID());
        domainEventPublisher.publish(new MessageBody<>(source.getType(),source),headers);

    }
}
