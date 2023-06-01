package br.com.delivery.eats.restaurant.messaging.config;

import br.com.delivery.eats.common.domain.events.DomainEventType;
import br.com.delivery.eats.common.domain.exception.DomainException;
import br.com.delivery.eats.common.messaging.domain.MessageWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.converter.AbstractMessageConverter;

import java.nio.charset.StandardCharsets;
import java.util.Map;


@RequiredArgsConstructor
class MessageWrapperConverter extends AbstractMessageConverter {

    private static final String BODY = "body";
    private static final String EVENT = "event";
    private final ObjectMapper objectMapper;
    private final Map<DomainEventType, Class<?>> domainEventTypeClassMap;

    @Override
    protected boolean supports(final Class<?> clazz) {
        return clazz.isAssignableFrom(MessageWrapper.class);
    }

    @Override
    protected MessageWrapper<?> convertFromInternal(final Message<?> message, final Class<?> targetClass, final Object conversionHint) {
        final var payload = message.getPayload();
        final var content = payload instanceof byte[] bytearrayPayload ?
            new String(bytearrayPayload, StandardCharsets.UTF_8) : (String) payload;
        return convertContentToMessageWrapper(targetClass, content, message.getHeaders());
    }

    private MessageWrapper<?> convertContentToMessageWrapper(final Class<?> targetClass, final String content, final MessageHeaders headers) {
        try {
            final var jsonNode = objectMapper.readTree(content);
            final var eventString = jsonNode.get(BODY).get(EVENT);
            final var domainEventType = DomainEventType.fromJsonNode(eventString);
            final var javaType = objectMapper.getTypeFactory().constructParametricType(targetClass, domainEventTypeClassMap.get(domainEventType));
            final var payerWrapper = (MessageWrapper<?>) objectMapper.readValue(jsonNode.toString(), javaType);
            return payerWrapper;
        } catch (JsonProcessingException e) {
            if (e.getCause() instanceof DomainException ddaBusinessException)
                throw new DomainException(ddaBusinessException.getMessage());
            else throw new DomainException("Error trying to read message", e);
        }
    }
}