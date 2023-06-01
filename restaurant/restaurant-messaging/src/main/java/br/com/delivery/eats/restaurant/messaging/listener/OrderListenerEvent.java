package br.com.delivery.eats.restaurant.messaging.listener;


import br.com.delivery.eats.common.messaging.domain.MessageWrapper;
import br.com.delivery.eats.restaurant.domain.application.ports.input.RestaurantDomainApplicationServicePort;
import br.com.delivery.eats.restaurant.domain.core.RestaurantDomainPort;
import br.com.delivery.eats.restaurant.domain.core.entity.Restaurant;
import br.com.delivery.eats.restaurant.messaging.domain.dto.OrderMessageEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

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
            OrderMessageEvent source = payload.getBody().getSource();
            Restaurant restaurantProcessed = restaurantDomainPort.handleRestaurant(source.getRestaurantId(), source.getType());
        };
    }
}