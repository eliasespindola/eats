package br.com.delivery.eats.restaurant.container;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "br.com.delivery.eats.restaurant")
@EntityScan(basePackages = "br.com.delivery.eats.restaurant")
@SpringBootApplication(scanBasePackages = "br.com.delivery.eats.restaurant")
public class RestaurantContainerApplication {
    public static void main(String[] args) {
        SpringApplication.run(RestaurantContainerApplication.class, args);
    }
}
