package br.com.delivery.eats.restaurant.messaging.listener;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

import java.util.function.Consumer;

@Configuration
public class OrderListenerEvent {

    @Bean
    public Consumer<Message<OrderMessageEvent>> consumer(){
        return new Consumer<Message<OrderMessageEvent>>() {
            @Override
            public void accept(Message message) {
                System.out.println("test");
            }
        };
    }
}