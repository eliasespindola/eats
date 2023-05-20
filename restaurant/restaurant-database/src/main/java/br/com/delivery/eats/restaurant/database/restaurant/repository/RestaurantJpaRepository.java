package br.com.delivery.eats.restaurant.database.restaurant.repository;


import br.com.delivery.eats.restaurant.database.restaurant.entity.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RestaurantJpaRepository extends JpaRepository<RestaurantEntity, UUID> {

}
