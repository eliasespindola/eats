package br.com.delivery.eats.order.domain.messaging.listener;

import br.com.delivery.eats.common.messaging.event.listener.MessageNotifier;
import br.com.delivery.eats.order.database.entity.events.DomainEventPendingEntity;
import br.com.delivery.eats.order.database.entity.events.OrderEventEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderEventEntityListener {

    private final MessageNotifier<OrderEventEntity> orderEventEntityMessageNotifier;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onSave(final DomainEventPendingEntity orderEventEntity) {
        orderEventEntityMessageNotifier.notify(orderEventEntity);
    }

}
