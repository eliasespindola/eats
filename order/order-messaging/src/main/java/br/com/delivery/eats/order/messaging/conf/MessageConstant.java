package br.com.delivery.eats.order.messaging.conf;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class MessageConstant {

    @NoArgsConstructor(access = PRIVATE)
    public static class Kafka {

        public static final String PRODUCER_TO_TOPIC = "producer-out-0";
    }
}
