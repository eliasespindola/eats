package br.com.delivery.eats.restaurant.domain.application.mapper;

import br.com.delivery.eats.common.domain.mapper.Mapper;
import br.com.delivery.eats.restaurant.domain.application.dto.ProductResponse;
import br.com.delivery.eats.restaurant.domain.core.entity.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductToProductResponse implements Mapper<List<Product>, List<ProductResponse>> {

    @Override
    public List<ProductResponse> map(List<Product> products) {
        return products.stream().map(product -> {
            return ProductResponse.builder()
                    .productId(product.getId().getValue())
                    .productName(product.getName())
                    .productPrice(product.getPrice().getAmount())
                    .productAvailable(product.getAvailable())
                    .build();
        }).collect(Collectors.toList());
    }
}
