package br.com.delivery.eats.restaurant.messaging.listener;


import br.com.delivery.eats.common.domain.events.DomainEventType;
import br.com.delivery.eats.common.messaging.domain.MessageWrapper;
import br.com.delivery.eats.restaurant.domain.application.ports.input.RestaurantDomainApplicationServicePort;
import br.com.delivery.eats.restaurant.domain.core.entity.Restaurant;
import br.com.delivery.eats.restaurant.messaging.domain.dto.OrderMessageEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.converter.MessagingMessageConverter;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.messaging.Message;

import java.util.UUID;
import java.util.function.Consumer;

@Configuration
public class OrderListenerEvent {

    private final RestaurantDomainApplicationServicePort restaurantDomainPort;

    public OrderListenerEvent(RestaurantDomainApplicationServicePort restaurantDomainPort) {
        this.restaurantDomainPort = restaurantDomainPort;
    }

    @Bean
    public Consumer<Message<MessageWrapper<OrderMessageEvent>>> consumer(){
        return (message) -> {
            // Acessar a mensagem recebida
            MessageWrapper<OrderMessageEvent> payload = message.getPayload();
//            OrderMessageEvent source = payload.getBody().getSource();

            Restaurant restaurantProcessed = restaurantDomainPort.handleRestaurant(UUID.fromString("f31448d6-f75b-11ed-acf3-0242ac120002"), DomainEventType.ORDER_PENDING_EVENT);
            System.out.println("bla");
        };
    }

}