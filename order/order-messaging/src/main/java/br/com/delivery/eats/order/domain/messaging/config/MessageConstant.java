package br.com.delivery.eats.order.domain.messaging.config;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class MessageConstant {

    @NoArgsConstructor(access = PRIVATE)
    public static class Kafka {
        public static final String OUTBOUND_PRODUCER = "producer-out-0";

    }
}
