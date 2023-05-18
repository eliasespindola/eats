package br.com.delivery.eats.order.web.rest;

import br.com.delivery.eats.order.domain.application.dto.create.CreateOrderRequest;
import br.com.delivery.eats.order.domain.application.dto.create.CreateOrderResponse;
import br.com.delivery.eats.order.domain.application.dto.track.TrackOrderRequest;
import br.com.delivery.eats.order.domain.application.dto.track.TrackOrderResponse;
import br.com.delivery.eats.order.domain.application.ports.input.OrderDomainApplicationService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(value = "/orders", produces = "application/vnd.api.v1+json")
public class OrderController {

    private final OrderDomainApplicationService orderDomainApplicationService;

    public OrderController(OrderDomainApplicationService orderDomainApplicationService) {
        this.orderDomainApplicationService = orderDomainApplicationService;
    }

    @PostMapping
    public ResponseEntity<CreateOrderResponse> createOrder(@RequestBody @Valid CreateOrderRequest createOrderRequest){
        log.info("Iniciando processo de criação do pedido do usuario {} para o restaurante {}", createOrderRequest.getCustomerId(), createOrderRequest.getRestaurantId());
        CreateOrderResponse response = orderDomainApplicationService.createOrder(createOrderRequest);
        log.info("Pedido criado! Com o seguinte rastreio id: {}", response.getOrderTrackingId());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{trackingId}")
    public ResponseEntity<TrackOrderResponse> getOrderByTrackingId(@PathVariable UUID trackingId) {
        log.info("Retornando o rastreio do pedido: {}",trackingId);
        TrackOrderResponse trackOrderResponse = orderDomainApplicationService.trackOrder(TrackOrderRequest.builder().orderTrackingId(trackingId).build());
        log.info("Retornando o rastreio do pedido: {} com o status: {}", trackOrderResponse.getOrderTrackingId(), trackOrderResponse.getOrderStatus());
        return  ResponseEntity.ok(trackOrderResponse);
    }
}
