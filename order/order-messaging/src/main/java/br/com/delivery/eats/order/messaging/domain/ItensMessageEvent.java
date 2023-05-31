package br.com.delivery.eats.order.messaging.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItensMessageEvent {
    private Long id;
    private ProductMessageEvent product;
}
