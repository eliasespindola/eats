package br.com.delivery.eats.order.messaging.mapper;

import br.com.delivery.eats.common.domain.mapper.Mapper;
import br.com.delivery.eats.common.domain.valueobject.Money;
import br.com.delivery.eats.common.domain.valueobject.OrderId;
import br.com.delivery.eats.common.domain.valueobject.ProductId;
import br.com.delivery.eats.order.database.entity.order.entity.OrderEntity;
import br.com.delivery.eats.order.database.entity.order.entity.OrderItemEntity;
import br.com.delivery.eats.order.domain.core.entity.OrderItem;
import br.com.delivery.eats.order.domain.core.entity.Product;
import br.com.delivery.eats.order.domain.core.valueobject.OrderItemId;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderItemEntityToOrderItemMapper implements Mapper<List<OrderItemEntity>, List<OrderItem>> {

    @Override
    public List<OrderItem> map(List<OrderItemEntity> orderItemEntities) {
        return orderItemEntities.stream().map(item -> {
            return OrderItem.builder()
                    .orderItemId(new OrderItemId(item.getId()))
                    .orderId(new OrderId(item.getOrder().getId()))
                    .product(new Product(new ProductId(item.getProductId())))
                    .quantity(item.getQuantity())
                    .price(new Money(item.getPrice()))
                    .subTotal(new Money(item.getSubTotal()))
                    .build();
        }).collect(Collectors.toList());
    }
}
