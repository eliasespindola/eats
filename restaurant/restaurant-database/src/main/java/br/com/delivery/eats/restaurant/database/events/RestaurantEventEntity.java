package br.com.delivery.eats.restaurant.database.events;

import br.com.delivery.eats.common.database.entity.event.DomainEventEntity;
import br.com.delivery.eats.common.domain.events.DomainEventType;
import br.com.delivery.eats.restaurant.database.restaurant.entity.RestaurantEntity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.ToString;

import static jakarta.persistence.CascadeType.MERGE;
import static jakarta.persistence.CascadeType.PERSIST;

@MappedSuperclass
@ToString(callSuper = true)
public abstract class RestaurantEventEntity extends DomainEventEntity<RestaurantEntity> {

    @ManyToOne(cascade = {PERSIST, MERGE})
    @JoinColumn(name = "ID_AGGREGATE")
    private final RestaurantEntity restaurantEntity;

    public RestaurantEventEntity(final String correlationId, final DomainEventType type, final RestaurantEntity restaurantEntity) {
        super(correlationId,type);
        this.restaurantEntity = restaurantEntity;
    }

    @Override
    public RestaurantEntity getSource() {
        return restaurantEntity;
    }
}
