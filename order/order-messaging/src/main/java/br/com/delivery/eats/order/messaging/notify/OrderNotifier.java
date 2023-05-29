package br.com.delivery.eats.order.messaging.notify;

import br.com.delivery.eats.common.messaging.event.listener.MessageNotifier;
import br.com.delivery.eats.order.domain.core.events.OrderCreatedEvent;
import br.com.delivery.eats.order.messaging.mapper.OrderEntityToOrderEvent;
import org.springframework.stereotype.Component;

@Component
public class OrderNotifier implements MessageNotifier<OrderCreatedEvent> {



    @Override
    public void notify(OrderCreatedEvent source) {
        System.out.println("notificado");

    }
}
