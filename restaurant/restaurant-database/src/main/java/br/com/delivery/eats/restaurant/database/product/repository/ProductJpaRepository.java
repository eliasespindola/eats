package br.com.delivery.eats.restaurant.database.product.repository;


import br.com.delivery.eats.restaurant.database.product.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductJpaRepository extends JpaRepository<ProductEntity, UUID> {

}
