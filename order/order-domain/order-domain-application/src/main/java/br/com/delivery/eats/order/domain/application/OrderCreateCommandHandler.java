package br.com.delivery.eats.order.domain.application;

import br.com.delivery.eats.common.domain.mapper.Mapper;
import br.com.delivery.eats.order.domain.application.dto.create.CreateOrderRequest;
import br.com.delivery.eats.order.domain.application.dto.create.CreateOrderResponse;
import br.com.delivery.eats.order.domain.application.ports.output.CustomerRepository;
import br.com.delivery.eats.order.domain.application.ports.output.OrderRepository;
import br.com.delivery.eats.order.domain.application.ports.output.RestaurantClientPort;
import br.com.delivery.eats.order.domain.core.OrderDomainPort;
import br.com.delivery.eats.order.domain.core.entity.Customer;
import br.com.delivery.eats.order.domain.core.entity.Order;
import br.com.delivery.eats.order.domain.core.entity.OrderItem;
import br.com.delivery.eats.order.domain.core.entity.Restaurant;
import br.com.delivery.eats.order.domain.core.exception.OrderDomainException;
import br.com.delivery.eats.order.domain.core.valueobject.StreetAddress;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
    private final Mapper<Order, CreateOrderResponse> responseMapper;

    private final OrderDomainPort domainPort;

    private final RestaurantClientPort restaurantClientPort;

    private final CustomerRepository customerRepository;

    private final OrderRepository orderRepository;


    public OrderCreateCommandHandler(Mapper<CreateOrderRequest, Restaurant> restaurantMapper,
                                     Mapper<CreateOrderRequest, StreetAddress> streetAddressMapper,
                                     Mapper<CreateOrderRequest, List<OrderItem>> orderItemMapper,
                                     Mapper<CreateOrderRequest, Order> orderMapper,
                                     Mapper<Order, CreateOrderResponse> responseMapper,
                                     OrderDomainPort domainPort,
                                     RestaurantClientPort restaurantClientPort,
                                     CustomerRepository customerRepository,
                                     OrderRepository orderRepository) {
        this.restaurantMapper = restaurantMapper;
        this.streetAddressMapper = streetAddressMapper;
        this.orderItemMapper = orderItemMapper;
        this.orderMapper = orderMapper;
        this.responseMapper = responseMapper;
        this.domainPort = domainPort;
        this.restaurantClientPort = restaurantClientPort;
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
    }



    @Transactional
    public CreateOrderResponse createOrder(CreateOrderRequest createOrderCommand) {
        final StreetAddress streetAddress = streetAddressMapper.map(createOrderCommand);
        final List<OrderItem> orderItems = orderItemMapper.map(createOrderCommand);
        checkCustomer(createOrderCommand.getCustomerId());
        Restaurant restaurant = checkRestaurant(restaurantMapper.map(createOrderCommand));
        Order order = orderMapper.map(createOrderCommand);
        domainPort.validateAndInitiateOrder(order, restaurant);
        Order orderSaved = saveOrder(order);
        log.info("Pedido criado com sucesso com o rastreio de id : {}", orderSaved.getTrackingId().getValue());
        return responseMapper.map(order);
    }



    private Restaurant checkRestaurant(Restaurant restaurant) {
        return restaurantClientPort.findById(restaurant);
    }

    private void checkCustomer(UUID customerId) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if (customer.isEmpty()) {
            log.warn("Could not find customer with customer id: {}", customerId);
            throw new OrderDomainException("Could not find customer with customer id: " + customer);
        }
    }

    Order saveOrder(Order order) {
        Order orderResult = orderRepository.save(order);
        if (orderResult == null) {
            log.error("Could not save order!");
            throw new OrderDomainException("Could not save order!");
        }
        log.info("Order is saved with id: {}", orderResult.getId().getValue());
        return orderResult;
    }
}
