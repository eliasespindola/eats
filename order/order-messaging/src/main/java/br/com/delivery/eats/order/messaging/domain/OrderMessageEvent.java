package br.com.delivery.eats.order.messaging.domain;

import br.com.delivery.eats.common.domain.events.DomainEventType;
import br.com.delivery.eats.common.domain.valueobject.OrderStatus;
import br.com.delivery.eats.order.domain.core.valueobject.StreetAddress;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;


@Data
@Builder
public class OrderMessageEvent {
    private UUID id;
    private DomainEventType type;
    private ZonedDateTime createdAt;
    private UUID customerId;
    private UUID restaurantId;
    private StreetAddress deliveryAddress;
    private List<ItensMessageEvent> items;
    private BigDecimal total;
    private UUID trackingId;
    private OrderStatus status;
}
