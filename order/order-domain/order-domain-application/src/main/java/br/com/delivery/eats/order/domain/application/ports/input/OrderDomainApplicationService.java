package br.com.delivery.eats.order.domain.application.ports.input;

import br.com.delivery.eats.order.domain.application.dto.create.CreateOrderRequest;
import br.com.delivery.eats.order.domain.application.dto.create.CreateOrderResponse;
import br.com.delivery.eats.order.domain.application.dto.track.TrackOrderRequest;
import br.com.delivery.eats.order.domain.application.dto.track.TrackOrderResponse;
import jakarta.validation.Valid;

public interface OrderDomainApplicationService {
    CreateOrderResponse createOrder(@Valid CreateOrderRequest createOrderCommand);

    TrackOrderResponse trackOrder(@Valid TrackOrderRequest trackOrderQuery);
}
