package br.com.delivery.eats.restaurant.domain.application.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Builder
public class RestaurantResponse {
    private UUID productId;
    private UUID restaurantId;
    private Boolean restaurantActive;
    private String restaurantName;

    private Boolean productAvailable;
    private BigDecimal productPrice;

    private String productName;
}
