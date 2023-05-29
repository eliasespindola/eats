package br.com.delivery.eats.order.database.entity.events;



import br.com.delivery.eats.order.database.entity.order.entity.OrderEntity;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import static br.com.delivery.eats.common.domain.events.DomainEventType.ORDER_PENDING_EVENT;


@Entity
@DiscriminatorValue("ORDER_PENDING_EVENT")
public class DomainEventPendingEntity extends OrderEventEntity{
    public DomainEventPendingEntity(String correlationId, OrderEntity orderEntity) {
        super(correlationId, ORDER_PENDING_EVENT, orderEntity);
    }


    public DomainEventPendingEntity() {
        super(null,null,null);
    }
}
