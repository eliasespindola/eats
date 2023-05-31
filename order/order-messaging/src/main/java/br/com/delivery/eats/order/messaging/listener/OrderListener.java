package br.com.delivery.eats.order.messaging.listener;

import br.com.delivery.eats.order.database.entity.events.OrderEventEntity;
import br.com.delivery.eats.order.messaging.mapper.OrderEntityToOrderEventMapper;
import br.com.delivery.eats.order.messaging.notify.OrderNotifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class OrderListener {
    private final OrderNotifier orderNotifier;
    private final OrderEntityToOrderEventMapper orderEntityToOrderEventMapper;

    public OrderListener(OrderNotifier orderNotifier, OrderEntityToOrderEventMapper orderEntityToOrderEventMapper) {
        this.orderNotifier = orderNotifier;
        this.orderEntityToOrderEventMapper = orderEntityToOrderEventMapper;
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onSave(OrderEventEntity orderEventEntity){
        orderNotifier.notify(orderEntityToOrderEventMapper.map(orderEventEntity), orderEventEntity.getCorrelationId());
    }
}
