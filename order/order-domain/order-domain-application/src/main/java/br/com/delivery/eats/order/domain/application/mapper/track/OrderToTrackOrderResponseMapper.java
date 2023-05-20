package br.com.delivery.eats.order.domain.application.mapper.track;

import br.com.delivery.eats.common.domain.mapper.Mapper;
import br.com.delivery.eats.order.domain.application.dto.track.TrackOrderResponse;
import br.com.delivery.eats.order.domain.core.entity.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderToTrackOrderResponseMapper implements Mapper<Order, TrackOrderResponse> {

    public TrackOrderResponse map(Order order) {
        return TrackOrderResponse.builder()
                .orderTrackingId(order.getTrackingId().getValue())
                .orderStatus(order.getOrderStatus())
                .failureMessages(order.getFailureMessages())
                .build();
    }
}
