package br.com.delivery.eats.order.messaging.publisher;


import br.com.delivery.eats.common.messaging.domain.MessageBody;
import br.com.delivery.eats.common.messaging.domain.MessageWrapper;
import br.com.delivery.eats.common.messaging.event.publisher.DomainEventPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.Map;

import static br.com.delivery.eats.order.messaging.conf.MessageConstant.Kafka.PRODUCER_TO_TOPIC;


@Slf4j
@Component
@RequiredArgsConstructor
public class OutboundPublisher implements DomainEventPublisher {

    private final StreamBridge streamBridge;

    @Override
    public void publish(MessageBody event, Map headers) {
        log.info("Publishing on outbound Payload: {} Headers: {}", event, headers);
        streamBridge.send(PRODUCER_TO_TOPIC, MessageBuilder
                .withPayload(new MessageWrapper<>(event)).copyHeaders(headers).build());
    }
}
