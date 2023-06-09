package br.com.delivery.eats.restaurant.domain.application.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
@Builder
public class RestaurantResponse {
    private UUID id;
    private Boolean active;
    private String name;

    private List<ProductResponse> products;
}
