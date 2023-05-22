package br.com.delivery.eats.order.domain.messaging.resolver;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class OutboundPublisher implements DomainEventPublisher {

    private final StreamBridge streamBridge;

    @Override
    public void publish(final MessageBody<?> event, final Map<String, Object> headers) {
        log.info("Publishing on outbound Payload: {} Headers: {}", event, headers);
        streamBridge.send(OUTBOUND_PRODUCER, MessageBuilder
                .withPayload(new MessageWrapper<>(event)).copyHeaders(headers).build());
    }

}
