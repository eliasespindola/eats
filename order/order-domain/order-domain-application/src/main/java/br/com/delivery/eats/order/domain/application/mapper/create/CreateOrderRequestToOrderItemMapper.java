package br.com.delivery.eats.order.domain.application.mapper.create;

import br.com.delivery.eats.common.domain.mapper.Mapper;
import br.com.delivery.eats.common.domain.valueobject.Money;
import br.com.delivery.eats.common.domain.valueobject.ProductId;
import br.com.delivery.eats.order.domain.application.dto.create.CreateOrderRequest;
import br.com.delivery.eats.order.domain.core.entity.OrderItem;
import br.com.delivery.eats.order.domain.core.entity.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class CreateOrderRequestToOrderItemMapper implements Mapper<CreateOrderRequest, List<OrderItem>> {

    @Override
    public List<OrderItem> map(CreateOrderRequest createOrderRequest) {
        return createOrderRequest.getItems().stream().map(item -> {
            return OrderItem.builder()
                    .price(new Money(item.getPrice()))
                    .product(new Product(new ProductId(item.getProductId())))
                    .quantity(item.getQuantity())
                    .subTotal(new Money(item.getSubTotal()))
                    .build();
        }).collect(Collectors.toList());
    }
}
