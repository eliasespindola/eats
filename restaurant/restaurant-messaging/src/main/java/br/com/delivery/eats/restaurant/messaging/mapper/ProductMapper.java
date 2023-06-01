package br.com.delivery.eats.restaurant.messaging.mapper;

import br.com.delivery.eats.common.domain.mapper.Mapper;
import br.com.delivery.eats.common.domain.valueobject.Money;
import br.com.delivery.eats.common.domain.valueobject.ProductId;
import br.com.delivery.eats.common.domain.valueobject.Quantity;
import br.com.delivery.eats.common.domain.valueobject.RestaurantId;
import br.com.delivery.eats.restaurant.domain.core.entity.Product;
import br.com.delivery.eats.restaurant.domain.core.entity.Restaurant;
import br.com.delivery.eats.restaurant.messaging.domain.dto.ItensMessageEvent;
import br.com.delivery.eats.restaurant.messaging.domain.dto.OrderMessageEvent;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper implements Mapper<List<ItensMessageEvent>, List<Product>> {

    @Override
    public List<Product> map(List<ItensMessageEvent> itensMessageEvents) {
        return itensMessageEvents.stream().map(item -> Product.Builder
                .builder()
                        .id(new ProductId(item.getProduct().getId()))
                        .quantity(new Quantity(item.getProduct().getQuantity()))
                        .price(new Money(item.getProduct().getAmount()))
                        .subTotal(new Money(item.getProduct().getSubTotal()))
                        .build()
                ).collect(Collectors.toList());
    }
}
