package br.com.delivery.eats.restaurant.database.product.mapper;

import br.com.delivery.eats.common.domain.mapper.Mapper;
import br.com.delivery.eats.restaurant.database.product.entity.ProductEntity;
import br.com.delivery.eats.restaurant.domain.core.entity.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductToProductEntityMapper implements Mapper<List<Product>, List<ProductEntity>> {


    @Override
    public List<ProductEntity> map(List<Product> products) {
        return products.stream().map(product -> {
            return ProductEntity.builder()
                    .id(product.getId().getValue())
                    .available(product.getAvailable())
                    .name(product.getName())
                    .price(product.getPrice().getAmount())
                    .quantity(product.getQuantity().getValue())
                    .build();
        }).collect(Collectors.toList());
    }
}
