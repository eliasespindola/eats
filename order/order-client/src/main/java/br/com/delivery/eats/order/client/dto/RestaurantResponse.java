package br.com.delivery.eats.order.client.dto;

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
