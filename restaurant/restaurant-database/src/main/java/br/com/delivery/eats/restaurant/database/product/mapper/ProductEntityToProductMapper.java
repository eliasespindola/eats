package br.com.delivery.eats.restaurant.database.product.mapper;

import br.com.delivery.eats.common.domain.mapper.Mapper;
import br.com.delivery.eats.common.domain.valueobject.Money;
import br.com.delivery.eats.common.domain.valueobject.ProductId;
import br.com.delivery.eats.common.domain.valueobject.Quantity;
import br.com.delivery.eats.restaurant.database.product.entity.ProductEntity;
import br.com.delivery.eats.restaurant.domain.core.entity.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductEntityToProductMapper implements Mapper<List<ProductEntity>, List<Product>> {


    @Override
    public List<Product> map(List<ProductEntity> productEntities) {
        return productEntities.stream().map(product ->{
                return Product.Builder.builder()
                        .id(new ProductId(product.getId()))
                        .quantity(new Quantity(product.getQuantity()))
                        .name(product.getName())
                        .price(new Money(product.getPrice()))
                        .available(product.getAvailable())
                        .build();
        }).collect(Collectors.toList());
    }
}
