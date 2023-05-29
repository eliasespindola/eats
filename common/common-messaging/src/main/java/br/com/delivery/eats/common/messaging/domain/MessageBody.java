package br.com.delivery.eats.common.messaging.domain;



import br.com.delivery.eats.common.domain.events.DomainEventType;
import lombok.Getter;
import lombok.ToString;


@Getter
@ToString
public class MessageBody<T>  {

    private final DomainEventType event;

    private final T source;

    public MessageBody(final DomainEventType event, final T source) {
        this.event = event;
        this.source = source;
    }

}
