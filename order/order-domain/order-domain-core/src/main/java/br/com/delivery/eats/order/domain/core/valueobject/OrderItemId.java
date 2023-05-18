package br.com.delivery.eats.order.domain.core.valueobject;

import br.com.delivery.eats.common.domain.valueobject.BaseId;

public class OrderItemId extends BaseId<Long> {
    public OrderItemId(Long value) {
        super(value);
    }
}
