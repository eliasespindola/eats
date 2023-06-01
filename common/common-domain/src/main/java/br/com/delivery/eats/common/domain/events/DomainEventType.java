package br.com.delivery.eats.common.domain.events;

import br.com.delivery.eats.common.domain.exception.DomainException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.stream.Stream;

public enum DomainEventType {
    ORDER_PENDING_EVENT,
    ORDER_PENDING_PAID,
    ORDER_CANCELLED_EVENT,
    ORDER_CANCELLING_EVENT,
    ORDER_APPROVED_EVENT,
    ORDER_PROCESSING_EVENT,

    RESTAURANT_PROCESSING_DONE_EVENT,

    RESTAURANT_PROCESSING_ERROR_EVENT;

    @JsonCreator
    public static DomainEventType fromJsonNode(final JsonNode jsonNode) {
        final var value = jsonNode.asText();
        return Stream.of(values())
                .filter(domainEventType -> domainEventType.name().equals(value))
                .findFirst()
                .orElseThrow(() -> new DomainException(String.format("Invalid event type [%s]", value)));
    }
}
