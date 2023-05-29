package br.com.delivery.eats.order.domain.messaging.mapper;

import br.com.uber.eats.common.domain.events.mapper.DomainEventMapper;
import br.com.uber.eats.order.domain.application.dto.create.OrderItem;
import br.com.uber.eats.order.domain.core.events.OrderCreatedEvent;
import br.com.uber.eats.order.messaging.domain.OrderMessaging;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderMessagingCreateMapper implements DomainEventMapper<OrderCreatedEvent,OrderMessaging> {

    @Override
    public OrderMessaging mapper(OrderCreatedEvent orderCreatedEvent) {
        return OrderMessaging.Builder.newBuilder()
                .customerId(orderCreatedEvent.getSource().getCustomerId())
                .restaurantId(orderCreatedEvent.getSource().getRestaurantId())
                .deliveryAddress(orderCreatedEvent.getSource().getDeliveryAddress())
                .price(orderCreatedEvent.getSource().getPrice())
                .items(convertOrderItemToOrderMessagingItem(orderCreatedEvent.getSource().getItems()))
                .trackingId(orderCreatedEvent.getSource().getTrackingId())
                .orderStatus(orderCreatedEvent.getSource().getOrderStatus())
                .build();
    }

    private List<OrderItem> convertOrderItemToOrderMessagingItem(List<br.com.uber.eats.order.domain.core.entity.OrderItem> items) {
        return items.stream().map(item -> OrderItem.builder()
                        .orderId(item.getOrderId())
                        .productId(item.getProduct().getId().getValue())
                        .quantity(item.getQuantity())
                        .price(item.getPrice().getAmount())
                        .subTotal(item.getSubTotal().getAmount())
                        .build()
                ).collect(Collectors.toList());

    }
}
