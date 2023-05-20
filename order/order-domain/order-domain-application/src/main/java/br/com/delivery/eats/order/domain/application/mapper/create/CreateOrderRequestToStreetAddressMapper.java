package br.com.delivery.eats.order.domain.application.mapper.create;

import br.com.delivery.eats.common.domain.mapper.Mapper;
import br.com.delivery.eats.order.domain.application.dto.create.CreateOrderRequest;
import br.com.delivery.eats.order.domain.core.valueobject.StreetAddress;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CreateOrderRequestToStreetAddressMapper implements Mapper<CreateOrderRequest, StreetAddress> {

    @Override
    public StreetAddress map(CreateOrderRequest createOrderRequest) {
        return StreetAddress.builder()
                .id(UUID.randomUUID())
                .street(createOrderRequest.getAddress().getStreet())
                .city(createOrderRequest.getAddress().getCity())
                .postalCode(createOrderRequest.getAddress().getPostalCode())
                .build();
    }
}
