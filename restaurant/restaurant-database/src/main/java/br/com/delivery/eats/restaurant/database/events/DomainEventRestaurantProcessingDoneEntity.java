package br.com.delivery.eats.restaurant.database.events;


import br.com.delivery.eats.restaurant.database.restaurant.entity.RestaurantEntity;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import static br.com.delivery.eats.common.domain.events.DomainEventType.RESTAURANT_PROCESSING_DONE_EVENT;


@Entity
@DiscriminatorValue("ORDER_PENDING_EVENT")
public class DomainEventRestaurantProcessingDoneEntity extends RestaurantEventEntity {
    public DomainEventRestaurantProcessingDoneEntity(String correlationId, RestaurantEntity restaurantEntity) {
        super(correlationId, RESTAURANT_PROCESSING_DONE_EVENT, restaurantEntity);
    }


    public DomainEventRestaurantProcessingDoneEntity() {
        super(null,null,null);
    }
}
