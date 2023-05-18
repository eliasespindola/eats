package br.com.delivery.eats.order.domain.application.mapper.create;

import br.com.delivery.eats.common.domain.mapper.Mapper;
import br.com.delivery.eats.order.domain.application.dto.create.CreateOrderRequest;
import br.com.delivery.eats.order.domain.core.valueobject.StreetAddress;
import org.springframework.stereotype.Component;

@Component
public class CreateOrderRequestStreetAddressToStreetAddressMapper implements Mapper<CreateOrderRequest, StreetAddress> {

    @Override
    public StreetAddress map(CreateOrderRequest createOrderRequest) {
        return StreetAddress.builder()
                .street(createOrderRequest.getAddress().getStreet())
                .city(createOrderRequest.getAddress().getCity())
                .postalCode(createOrderRequest.getAddress().getPostalCode())
                .build();
    }
}
