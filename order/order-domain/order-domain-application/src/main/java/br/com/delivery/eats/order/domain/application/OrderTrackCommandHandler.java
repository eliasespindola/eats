package br.com.delivery.eats.order.domain.application;

import br.com.delivery.eats.order.domain.application.dto.track.TrackOrderRequest;
import br.com.delivery.eats.order.domain.application.dto.track.TrackOrderResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderTrackCommandHandler {
    public TrackOrderResponse trackOrder(TrackOrderRequest trackOrderQuery) {
        return null;
    }
}
