package br.com.delivery.eats.restaurant.messaging.listener;


import br.com.delivery.eats.common.messaging.domain.MessageWrapper;
import br.com.delivery.eats.restaurant.messaging.domain.dto.OrderMessageEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

import java.util.function.Consumer;

@Configuration
public class OrderListenerEvent {

    @Bean
    public Consumer<Message<MessageWrapper<OrderMessageEvent>>> consumer(){
        return message -> System.out.println(message.toString());
    }
}