package br.com.delivery.eats.order.database.restaurant.exception;

public class RestaurantDataAccessException extends RuntimeException{

    public RestaurantDataAccessException(String message) {
        super(message);
    }
}
