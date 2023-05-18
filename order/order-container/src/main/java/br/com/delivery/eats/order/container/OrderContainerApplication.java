package br.com.delivery.eats.order.container;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "br.com.delivery.eats.order")
public class OrderContainerApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderContainerApplication.class, args);
    }
}
