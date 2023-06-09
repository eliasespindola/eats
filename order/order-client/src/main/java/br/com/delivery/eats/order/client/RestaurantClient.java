package br.com.delivery.eats.order.client;

import br.com.delivery.eats.order.client.dto.RestaurantResponse;
import br.com.delivery.eats.order.client.error.RestaurantClientFeignConfig;
import feign.Headers;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(
        name = "restaurantClient",
        url = "http://localhost:8082/restaurants",
        configuration = RestaurantClientFeignConfig.class)
@Headers("Content-Type: application/json")
public interface RestaurantClient {

    @Retry(name = "restaurantClient")
    @CircuitBreaker(name = "restaurantClient")
    @GetMapping(value = "/{restaurantId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    RestaurantResponse getRestaurantById(@PathVariable(value = "restaurantId") final UUID restaurantId);
}
