package br.com.delivery.eats.order.domain.core.valueobject;

import br.com.delivery.eats.common.domain.valueobject.BaseId;

import java.util.UUID;

public class TrackingId extends BaseId<UUID> {
    public TrackingId(UUID value) {
        super(value);
    }
}
