package br.com.delivery.eats.order.domain.application;

import br.com.delivery.eats.common.domain.mapper.Mapper;
import br.com.delivery.eats.common.domain.valueobject.OrderStatus;
import br.com.delivery.eats.order.domain.application.dto.create.CreateOrderRequest;
import br.com.delivery.eats.order.domain.application.dto.create.CreateOrderResponse;
import br.com.delivery.eats.order.domain.application.ports.output.CustomerRepository;
import br.com.delivery.eats.order.domain.application.ports.output.RestaurantRepository;
import br.com.delivery.eats.order.domain.core.OrderDomainPort;
import br.com.delivery.eats.order.domain.core.entity.Customer;
import br.com.delivery.eats.order.domain.core.entity.Order;
import br.com.delivery.eats.order.domain.core.entity.OrderItem;
import br.com.delivery.eats.order.domain.core.entity.Restaurant;
import br.com.delivery.eats.order.domain.core.exception.OrderDomainException;
import br.com.delivery.eats.order.domain.core.valueobject.StreetAddress;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
public class OrderCreateCommandHandler {
    private final Mapper<CreateOrderRequest,Restaurant> restaurantMapper;
    private final Mapper<CreateOrderRequest,StreetAddress> streetAddressMapper;
    private final Mapper<CreateOrderRequest,List<OrderItem>> orderItemMapper;

    private final Mapper<CreateOrderRequest, Order> orderMapper;

    private final OrderDomainPort domainPort;

    private final RestaurantRepository restaurantRepository;

    private final CustomerRepository customerRepository;

    public OrderCreateCommandHandler(Mapper<CreateOrderRequest, Restaurant> restaurantMapper,
                                     Mapper<CreateOrderRequest, StreetAddress> streetAddressMapper,
                                     Mapper<CreateOrderRequest, List<OrderItem>> orderItemMapper,
                                     Mapper<CreateOrderRequest, Order> orderMapper,
                                     OrderDomainPort domainPort,
                                     RestaurantRepository restaurantRepository, CustomerRepository customerRepository) {
        this.restaurantMapper = restaurantMapper;
        this.streetAddressMapper = streetAddressMapper;
        this.orderItemMapper = orderItemMapper;
        this.orderMapper = orderMapper;
        this.domainPort = domainPort;
        this.restaurantRepository = restaurantRepository;
        this.customerRepository = customerRepository;
    }


    public CreateOrderResponse createOrder(CreateOrderRequest createOrderCommand) {
        final StreetAddress streetAddress = streetAddressMapper.map(createOrderCommand);
        final List<OrderItem> orderItems = orderItemMapper.map(createOrderCommand);
        checkCustomer(createOrderCommand.getCustomerId());
        Restaurant restaurant = checkRestaurant(restaurantMapper.map(createOrderCommand));
        Order order = orderMapper.map(createOrderCommand);
        domainPort.validateAndInitiateOrder(order,restaurant);

        log.info("Pedido criado com sucesso com o id: {}", order.getId());

        return CreateOrderResponse.builder()
                .orderStatus(OrderStatus.PENDING)
                .orderTrackingId(UUID.randomUUID())
                .message("Pending")
                .build();
    }

    private Restaurant checkRestaurant(Restaurant restaurant) {
        Optional<Restaurant> possibleRestaurant = restaurantRepository.findById(restaurant);
        if(possibleRestaurant.isEmpty()){
            log.warn("Could not find restaurant with restaurant id: {}", restaurant.getId());
            throw new OrderDomainException("Could not find restaurant with restaurant id: " +
                    restaurant.getId());
        }
        return possibleRestaurant.get();
    }

    private void checkCustomer(UUID customerId) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if (customer.isEmpty()) {
            log.warn("Could not find customer with customer id: {}", customerId);
            throw new OrderDomainException("Could not find customer with customer id: " + customer);
        }
    }
}
