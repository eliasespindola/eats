package br.com.delivery.eats.order.domain.core.entity;

import br.com.delivery.eats.common.domain.entity.AggregateRoot;
import br.com.delivery.eats.common.domain.valueobject.CustomerId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class Customer extends AggregateRoot<CustomerId> {

    private String username;
    private String firstName;
    private String lastName;

    public Customer(CustomerId customerId) {
        super.setId(customerId);
    }

}
