package br.com.delivery.eats.restaurant.container;

import br.com.delivery.eats.restaurant.domain.core.RestaurantDomainAdapter;
import br.com.delivery.eats.restaurant.domain.core.RestaurantDomainPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public RestaurantDomainPort orderDomainService() {
        return new RestaurantDomainAdapter();
    }
}
