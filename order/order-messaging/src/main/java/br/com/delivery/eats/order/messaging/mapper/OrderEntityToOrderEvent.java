package br.com.delivery.eats.order.messaging.mapper;

import br.com.delivery.eats.common.domain.mapper.Mapper;
import br.com.delivery.eats.order.database.entity.events.OrderEventEntity;
import br.com.delivery.eats.order.database.entity.order.entity.OrderEntity;
import br.com.delivery.eats.order.domain.core.entity.Order;
import br.com.delivery.eats.order.domain.core.events.OrderCreatedEvent;
import org.springframework.stereotype.Component;

@Component
public class OrderEntityToOrderEvent implements Mapper<OrderEventEntity, OrderCreatedEvent> {

    private final Mapper<OrderEntity, Order> orderEntityToOrderMapper;

    public OrderEntityToOrderEvent(Mapper<OrderEntity, Order> orderEntityToOrderMapper) {
        this.orderEntityToOrderMapper = orderEntityToOrderMapper;
    }

    @Override
    public OrderCreatedEvent map(OrderEventEntity orderEventEntity) {
        return OrderCreatedEvent.Builder.newBuilder()
                .withOrder(orderEntityToOrderMapper.map(orderEventEntity.getSource()))
                .build();
    }
}
