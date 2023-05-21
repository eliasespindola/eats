package br.com.delivery.eats.order.database.entity.order.adapter;


import br.com.delivery.eats.common.domain.valueobject.OrderId;
import br.com.delivery.eats.order.database.entity.order.repository.OrderJpaRepository;
import br.com.delivery.eats.order.database.entity.order.mapper.OrderDataAccessMapper;
import br.com.delivery.eats.order.domain.application.ports.output.OrderRepository;
import br.com.delivery.eats.order.domain.core.entity.Order;
import br.com.delivery.eats.order.domain.core.valueobject.TrackingId;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class OrderRepositoryImpl implements OrderRepository {

    private final OrderJpaRepository orderJpaRepository;
    private final OrderDataAccessMapper orderDataAccessMapper;

    public OrderRepositoryImpl(OrderJpaRepository orderJpaRepository,
                               OrderDataAccessMapper orderDataAccessMapper) {
        this.orderJpaRepository = orderJpaRepository;
        this.orderDataAccessMapper = orderDataAccessMapper;
    }

    @Override
    public Order save(Order order) {
        return orderDataAccessMapper.orderEntityToOrder(orderJpaRepository
                .save(orderDataAccessMapper.orderToOrderEntity(order)));
    }

    @Override
    public Optional<Order> findById(OrderId orderId) {
        return orderJpaRepository.findById(orderId.getValue()).map(orderDataAccessMapper::orderEntityToOrder);
    }

    @Override
    public Optional<Order> findByTrackingId(TrackingId trackingId) {
        return orderJpaRepository.findByTrackingId(trackingId.getValue())
                .map(orderDataAccessMapper::orderEntityToOrder);
    }
}
