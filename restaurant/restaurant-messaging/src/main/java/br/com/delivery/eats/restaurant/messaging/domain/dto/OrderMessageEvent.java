package br.com.delivery.eats.restaurant.messaging.domain.dto;

import br.com.delivery.eats.common.domain.events.DomainEventType;
import br.com.delivery.eats.common.domain.valueobject.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderMessageEvent implements Serializable {
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
