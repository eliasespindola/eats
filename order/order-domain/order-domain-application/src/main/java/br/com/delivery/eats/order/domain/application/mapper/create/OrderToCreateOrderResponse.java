package br.com.delivery.eats.order.domain.application.mapper.create;

import br.com.delivery.eats.common.domain.mapper.Mapper;
import br.com.delivery.eats.order.domain.application.dto.create.CreateOrderResponse;
import br.com.delivery.eats.order.domain.core.entity.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderToCreateOrderResponse implements Mapper<Order, CreateOrderResponse> {
    @Override
    public CreateOrderResponse map(Order order) {
        return CreateOrderResponse.builder()
                .orderStatus(order.getOrderStatus())
                .orderTrackingId(order.getTrackingId().getValue())
                .message("Pedido criado com sucesso!")
                .build();
    }
}
