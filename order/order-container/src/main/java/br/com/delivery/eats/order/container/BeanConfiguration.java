package br.com.delivery.eats.order.container;

import br.com.delivery.eats.order.domain.core.OrderDomainAdapter;
import br.com.delivery.eats.order.domain.core.OrderDomainPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public OrderDomainPort orderDomainService() {
        return new OrderDomainAdapter();
    }
}
