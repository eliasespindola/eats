package br.com.delivery.eats.common.messaging.domain;



import br.com.delivery.eats.common.domain.events.DomainEventType;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@ToString
public class MessageBody<T>  {

    private DomainEventType event;

    private T source;

    public MessageBody(final DomainEventType event, final T source) {
        this.event = event;
        this.source = source;
    }

}
