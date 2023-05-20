package br.com.delivery.eats.restaurant.domain.application.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class RestaurantRequest {
    @NotNull
    private final UUID restaurantId;
}
