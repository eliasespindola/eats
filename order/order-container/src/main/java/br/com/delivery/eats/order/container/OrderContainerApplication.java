package br.com.delivery.eats.order.container;

import br.com.delivery.eats.order.client.RestaurantClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "br.com.delivery.eats.order")
@EntityScan(basePackages = "br.com.delivery.eats.order")
@SpringBootApplication(scanBasePackages = "br.com.delivery.eats.order")
@EnableFeignClients(clients = {RestaurantClient.class})
public class OrderContainerApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderContainerApplication.class, args);
    }
}
