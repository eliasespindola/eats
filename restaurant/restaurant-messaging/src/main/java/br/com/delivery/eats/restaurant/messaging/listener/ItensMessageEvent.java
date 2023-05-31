package br.com.delivery.eats.restaurant.messaging.listener;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItensMessageEvent {
    private Long id;
    private ProductMessageEvent product;
}
