package br.com.delivery.eats.order.database.entity.order.adapter;


import br.com.delivery.eats.common.domain.valueobject.OrderId;
import br.com.delivery.eats.order.database.entity.events.DomainEventPendingEntity;
import br.com.delivery.eats.order.database.entity.order.mapper.OrderDataAccessMapper;
import br.com.delivery.eats.order.database.entity.order.repository.OrderEntityJpaRepository;
import br.com.delivery.eats.order.database.entity.order.repository.OrderEventEntityJpaRepository;
import br.com.delivery.eats.order.domain.application.ports.output.OrderRepository;
import br.com.delivery.eats.order.domain.core.entity.Order;
import br.com.delivery.eats.order.domain.core.valueobject.TrackingId;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class OrderRepositoryImpl implements OrderRepository {

    private final OrderEventEntityJpaRepository orderEventEntityJpaRepository;
    private final OrderDataAccessMapper orderDataAccessMapper;

    private final OrderEntityJpaRepository orderEntityJpaRepository;


    public OrderRepositoryImpl(OrderEventEntityJpaRepository orderEventEntityJpaRepository,
                               OrderDataAccessMapper orderDataAccessMapper, OrderEntityJpaRepository orderEntityJpaRepository) {
        this.orderEventEntityJpaRepository = orderEventEntityJpaRepository;
        this.orderDataAccessMapper = orderDataAccessMapper;
        this.orderEntityJpaRepository = orderEntityJpaRepository;
    }

    @Override
    public Order save(Order order) {
        DomainEventPendingEntity orderEventEntity = orderEventEntityJpaRepository.save(orderDataAccessMapper.orderToEvent(order));
        return orderDataAccessMapper.orderEntityToOrder(orderEventEntity.getSource());
    }

    @Override
    public Optional<Order> findById(OrderId orderId) {
        return orderEventEntityJpaRepository.findById(orderId.getValue()).map(item -> orderDataAccessMapper.orderEntityToOrder(item.getSource()));
    }

    @Override
    public Optional<Order> findByTrackingId(TrackingId trackingId) {
        return orderEntityJpaRepository.findByTrackingId(trackingId.getValue())
                .map(orderDataAccessMapper::orderEntityToOrder);
    }
}
