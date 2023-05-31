package br.com.delivery.eats.order.domain.core;

import br.com.delivery.eats.order.domain.core.entity.Order;
import br.com.delivery.eats.order.domain.core.entity.Product;
import br.com.delivery.eats.order.domain.core.entity.Restaurant;
import br.com.delivery.eats.order.domain.core.exception.OrderDomainException;

import java.util.List;
import java.util.UUID;


public class OrderDomainAdapter implements OrderDomainPort {
    @Override
    public void validateAndInitiateOrder(final Order order, final Restaurant restaurant) {
        validateRestaurant(restaurant);
        setOrderProductInformation(order, restaurant);
        order.validateOrder();
        order.initializeOrder();
    }

    @Override
    public void payOrder(Order order, UUID uuid) {
        return;
    }

    @Override
    public void approveOrder(Order order) {
        return;
    }

    @Override
    public void cancelOrder(Order order, List<String> failureMessages) {
        return;
    }

    private void validateRestaurant(final Restaurant restaurant) {
        if(!restaurant.isActive()){
            throw new OrderDomainException("Restaurant with id " + restaurant.getId().getValue() + " is currently not active!");
        }
    }

    private void setOrderProductInformation(Order order, Restaurant restaurant) {
        order.getItems().forEach(orderItem -> restaurant.getProducts().forEach(restaurantProduct -> {
            Product currentProduct = orderItem.getProduct();
            if (currentProduct.equals(restaurantProduct)) {
                currentProduct.updateWithConfirmedNameAndPrice(restaurantProduct.getName(),
                        restaurantProduct.getPrice());
            }
        }));
    }
}
