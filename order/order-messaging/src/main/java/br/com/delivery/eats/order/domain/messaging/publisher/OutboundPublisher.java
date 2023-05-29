package br.com.delivery.eats.order.domain.messaging.publisher;

import br.com.delivery.eats.common.messaging.domain.MessageBody;
import br.com.delivery.eats.common.messaging.event.publisher.DomainEventPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class OutboundPublisher implements DomainEventPublisher {
    @Override
    public void publish(MessageBody event, Map headers) {
        System.out.println(event.getEvent());
    }
}
