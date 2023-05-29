package br.com.delivery.eats.order.domain.messaging.adapter;


import br.com.delivery.eats.common.messaging.event.publisher.DomainEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class ProducerEventAdapter implements ProducerEventPort<OrderEvent> {
    private final DomainEventPublisher domainEventPublisher;

    public ProducerEventAdapter(DomainEventPublisher domainEventPublisher) {
        this.domainEventPublisher = domainEventPublisher;
    }

    @Override
    public void execute(OrderEvent domainEventEntity) {
        domainEventPublisher.publish(domainEventEntity);
    }
}
