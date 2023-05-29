package br.com.delivery.eats.order.messaging.listener;

import br.com.delivery.eats.order.database.entity.events.OrderEventEntity;
import br.com.delivery.eats.order.messaging.mapper.OrderEntityToOrderEvent;
import br.com.delivery.eats.order.messaging.notify.OrderNotifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class OrderListener {
    private final OrderNotifier orderNotifier;
    private final OrderEntityToOrderEvent orderEntityToOrderEvent;

    public OrderListener(OrderNotifier orderNotifier, OrderEntityToOrderEvent orderEntityToOrderEvent) {
        this.orderNotifier = orderNotifier;
        this.orderEntityToOrderEvent = orderEntityToOrderEvent;
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onSave(OrderEventEntity orderEventEntity){
        System.out.println("notificou");
        orderNotifier.notify(orderEntityToOrderEvent.map(orderEventEntity));
    }
}
