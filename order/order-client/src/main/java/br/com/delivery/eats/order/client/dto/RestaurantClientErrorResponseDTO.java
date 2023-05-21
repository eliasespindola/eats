package br.com.delivery.eats.order.client.dto;


import java.time.LocalDateTime;

public record RestaurantClientErrorResponseDTO(
    Integer code,
    String error,
    String message,
//    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
//    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    LocalDateTime timestamp
) {

}
