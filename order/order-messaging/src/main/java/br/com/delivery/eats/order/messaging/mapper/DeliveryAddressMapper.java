package br.com.delivery.eats.order.messaging.mapper;

import br.com.delivery.eats.common.domain.mapper.Mapper;
import br.com.delivery.eats.order.database.entity.order.entity.OrderAddressEntity;
import br.com.delivery.eats.order.domain.core.valueobject.StreetAddress;
import org.springframework.stereotype.Component;

@Component
public class DeliveryAddressMapper implements Mapper<OrderAddressEntity, StreetAddress> {

    @Override
    public StreetAddress map(OrderAddressEntity orderAddressEntity) {
        return StreetAddress.builder()
                .id(orderAddressEntity.getId())
                .postalCode(orderAddressEntity.getPostalCode())
                .city(orderAddressEntity.getCity())
                .street(orderAddressEntity.getStreet())
                .build();
    }
}
