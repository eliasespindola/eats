package br.com.delivery.eats.restaurant.database.product.mapper;

import br.com.delivery.eats.common.domain.mapper.Mapper;
import br.com.delivery.eats.restaurant.database.product.entity.ProductEntity;
import br.com.delivery.eats.restaurant.domain.core.entity.Product;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductEntityToProductMapper implements Mapper<List<ProductEntity>, List<Product>> {


    @Override
    public List<Product> map(List<ProductEntity> productEntities) {
        return null;
    }
}
