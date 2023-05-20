package br.com.delivery.eats.order.domain.application;

import br.com.delivery.eats.order.domain.application.dto.track.TrackOrderRequest;
import br.com.delivery.eats.order.domain.application.dto.track.TrackOrderResponse;
import br.com.delivery.eats.order.domain.application.mapper.track.OrderToTrackOrderResponseMapper;
import br.com.delivery.eats.order.domain.application.ports.output.OrderRepository;
import br.com.delivery.eats.order.domain.core.entity.Order;
import br.com.delivery.eats.order.domain.core.exception.OrderDomainNotFoundException;
import br.com.delivery.eats.order.domain.core.valueobject.TrackingId;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class OrderTrackCommandHandler {
    private final OrderRepository orderRepository;
    private final OrderToTrackOrderResponseMapper orderToTrackOrderResponseMapper;

    public OrderTrackCommandHandler(OrderRepository orderRepository, OrderToTrackOrderResponseMapper orderToTrackOrderResponseMapper) {
        this.orderRepository = orderRepository;
        this.orderToTrackOrderResponseMapper = orderToTrackOrderResponseMapper;
    }

    public TrackOrderResponse trackOrder(TrackOrderRequest trackOrderQuery) {
        Optional<Order> orderResult = orderRepository.findByTrackingId(new TrackingId(trackOrderQuery.getOrderTrackingId()));
        if (orderResult.isEmpty()) {
            log.warn("Could not find order with tracking id: {}", trackOrderQuery.getOrderTrackingId());
            throw new OrderDomainNotFoundException("Could not find order with tracking id: " +
                    trackOrderQuery.getOrderTrackingId());
        }
        return orderToTrackOrderResponseMapper.map(orderResult.get());
    }
}
