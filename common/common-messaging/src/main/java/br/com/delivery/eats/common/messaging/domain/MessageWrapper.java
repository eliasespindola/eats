package br.com.delivery.eats.common.messaging.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import static lombok.AccessLevel.PRIVATE;


@Getter
@ToString
@NoArgsConstructor(force = true, access = PRIVATE)
public class MessageWrapper<T> {

    private final MessageBody<T> body;

    public MessageWrapper(final MessageBody<T> body) {
        this.body = body;
    }

}
