package br.com.delivery.eats.order.database.entity.events;

import br.com.delivery.eats.common.database.entity.event.DomainEventEntity;
import br.com.delivery.eats.common.database.entity.event.DomainEventType;
import br.com.delivery.eats.order.database.entity.order.entity.OrderEntity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.ToString;

import static jakarta.persistence.CascadeType.MERGE;
import static jakarta.persistence.CascadeType.PERSIST;

@MappedSuperclass
@ToString(callSuper = true)
public abstract class OrderEventEntity extends DomainEventEntity<OrderEntity> {

    @ManyToOne(cascade = {PERSIST, MERGE})
    @JoinColumn(name = "ID_AGGREGATE")
    private final OrderEntity orderEntity;

    public OrderEventEntity(final String correlationId, final DomainEventType type, final OrderEntity orderEntity) {
        super(correlationId,type);
        this.orderEntity = orderEntity;
    }

    @Override
    public OrderEntity getSource() {
        return orderEntity;
    }
}
