package br.com.delivery.eats.restaurant.database.product.mapper;

import br.com.delivery.eats.common.domain.mapper.Mapper;
import br.com.delivery.eats.restaurant.database.product.entity.ProductEntity;
import br.com.delivery.eats.restaurant.database.restaurant.entity.RestaurantEntity;
import br.com.delivery.eats.restaurant.domain.core.entity.Product;
import br.com.delivery.eats.restaurant.domain.core.entity.Restaurant;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductToProductEntityMapper implements Mapper<List<Product>, List<ProductEntity>> {


    @Override
    public List<ProductEntity> map(List<Product> products) {
        return null;
    }
}
