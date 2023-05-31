package br.com.delivery.eats.order.messaging.domain;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
public class ProductMessageEvent {
    private UUID id;
    private Integer quantity;
    private BigDecimal amount;
    private BigDecimal subTotal;


}
