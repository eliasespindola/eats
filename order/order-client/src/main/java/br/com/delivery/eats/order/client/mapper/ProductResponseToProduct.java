package br.com.delivery.eats.order.client.mapper;


import br.com.delivery.eats.common.domain.mapper.Mapper;
import br.com.delivery.eats.common.domain.valueobject.Money;
import br.com.delivery.eats.common.domain.valueobject.ProductId;
import br.com.delivery.eats.order.client.dto.ProductResponse;
import br.com.delivery.eats.order.domain.core.entity.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductResponseToProduct implements Mapper<List<ProductResponse>, List<Product>> {

    @Override
    public List<Product> map(List<ProductResponse> productResponses) {
        List<Product> products = productResponses.stream().map(product -> {
            return new Product(new ProductId(product.getId()), product.getName(), new Money(product.getPrice()));
        }).collect(Collectors.toList());
        return products;
    }
}
