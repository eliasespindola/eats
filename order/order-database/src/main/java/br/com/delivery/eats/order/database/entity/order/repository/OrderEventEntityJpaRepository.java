package br.com.delivery.eats.order.database.entity.order.repository;


import br.com.delivery.eats.order.database.entity.events.OrderEventEntity;
import br.com.delivery.eats.order.database.entity.order.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderEventEntityJpaRepository extends JpaRepository<OrderEventEntity, UUID> {

    @Query("SELECT o FROM OrderEntity o WHERE o.trackingId = :trackingId")
    Optional<OrderEntity> findByTrackingId(UUID trackingId);
}
