package br.com.delivery.eats.order.domain.core.entity;

import br.com.delivery.eats.common.domain.entity.BaseEntity;
import br.com.delivery.eats.common.domain.valueobject.Money;
import br.com.delivery.eats.common.domain.valueobject.OrderId;
import br.com.delivery.eats.order.domain.core.valueobject.OrderItemId;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderItem  extends BaseEntity<OrderItemId> {
    private OrderId orderId;
    private final Product product;
    private final int quantity;
    private final Money price;
    private final Money subTotal;
}
