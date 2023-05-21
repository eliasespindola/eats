package br.com.delivery.eats.order.client.error;

import feign.Logger;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;


public class RestaurantClientFeignConfig {

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    ErrorDecoder feingErrorDecoder() {
        return new RestaurantClientErrorDecoder();
    }
}
