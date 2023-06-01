package br.com.delivery.eats.restaurant.messaging.listener;


import br.com.delivery.eats.common.domain.mapper.Mapper;
import br.com.delivery.eats.common.messaging.domain.MessageWrapper;
import br.com.delivery.eats.restaurant.domain.application.ports.input.RestaurantDomainApplicationServicePort;
import br.com.delivery.eats.restaurant.domain.core.entity.Restaurant;
import br.com.delivery.eats.restaurant.messaging.domain.dto.OrderMessageEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

import java.util.function.Consumer;

@Configuration
public class OrderListenerEvent {

    private final RestaurantDomainApplicationServicePort restaurantDomainPort;
    private final Mapper<OrderMessageEvent, Restaurant> restaurantMapper;

    public OrderListenerEvent(RestaurantDomainApplicationServicePort restaurantDomainPort, Mapper<OrderMessageEvent, Restaurant> restaurantMapper) {
        this.restaurantDomainPort = restaurantDomainPort;
        this.restaurantMapper = restaurantMapper;
    }

    @Bean
    public Consumer<Message<MessageWrapper<OrderMessageEvent>>> consumer(){
        return (message) -> {
            MessageWrapper<OrderMessageEvent> payload = message.getPayload();
            Restaurant restaurantProcessed = restaurantDomainPort.handleRestaurant(restaurantMapper.map(payload.getBody().getSource()),payload.getBody().getEvent());
            System.out.println("bla");
        };
    }

}
