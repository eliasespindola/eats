package br.com.delivery.eats.order.domain.messaging.notify;

import br.com.delivery.eats.common.messaging.event.listener.MessageNotifier;
import br.com.delivery.eats.common.messaging.event.publisher.DomainEventPublisher;
import br.com.delivery.eats.order.database.entity.events.OrderEventEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@RequiredArgsConstructor
public class OrderEventEntityMessageNotifier implements MessageNotifier<OrderEventEntity> {

    private final DomainEventPublisher domainEventPublisher;
    @Override
    public void notify(OrderEventEntity orderEventEntity) {
        domainEventPublisher.publish();
    }
}
