package br.com.delivery.eats.order.messaging.notify;

import br.com.delivery.eats.common.messaging.domain.MessageBody;
import br.com.delivery.eats.common.messaging.event.listener.MessageNotifier;
import br.com.delivery.eats.common.messaging.event.publisher.DomainEventPublisher;
import br.com.delivery.eats.order.domain.core.events.OrderCreatedEvent;
import br.com.delivery.eats.order.messaging.domain.ItensMessageEvent;
import br.com.delivery.eats.order.messaging.domain.OrderMessageEvent;
import br.com.delivery.eats.order.messaging.domain.ProductMessageEvent;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class OrderNotifier implements MessageNotifier<OrderCreatedEvent> {

    private final DomainEventPublisher domainEventPublisher;

    public OrderNotifier(DomainEventPublisher domainEventPublisher) {
        this.domainEventPublisher = domainEventPublisher;
    }

    @Override
    public void notify(OrderCreatedEvent source, String correlationId) {
        HashMap<String, Object> headers = createHeaders(correlationId);
        OrderMessageEvent message = createPayload(source);
        domainEventPublisher.publish(new MessageBody<>(source.getType(),message),headers);

    }

    @NotNull
    private static HashMap<String, Object> createHeaders(String correlationId) {
        HashMap<String, Object> headers = new HashMap<>();
        headers.put("correlation-id", UUID.fromString(correlationId));
        return headers;
    }

    private OrderMessageEvent createPayload(OrderCreatedEvent source) {
        List<ItensMessageEvent> itensMessageEvents = createItensMessage(source);

        return OrderMessageEvent.builder()
                .id(source.getId())
                .type(source.getType())
                .createdAt(source.getCreatedAt())
                .customerId(source.getSource().getCustomerId().getValue())
                .restaurantId(source.getSource().getRestaurantId().getValue())
                .deliveryAddress(source.getSource().getDeliveryAddress())
                .items(itensMessageEvents)
                .total(source.getSource().getPrice().getAmount())
                .trackingId(source.getSource().getTrackingId().getValue())
                .status(source.getSource().getOrderStatus())
                .build();

    }

    @NotNull
    private  List<ItensMessageEvent> createItensMessage(OrderCreatedEvent source) {
        return source.getSource().getItems().stream().map(item -> ItensMessageEvent.builder()
                .id(item.getId().getValue())
                .product(ProductMessageEvent
                        .builder()
                        .id(item.getProduct().getId().getValue())
                        .amount(item.getPrice().getAmount())
                        .subTotal(item.getSubTotal().getAmount())
                        .quantity(item.getQuantity())
                        .build())
                .build()).collect(Collectors.toList());
    }
}
