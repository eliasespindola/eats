package br.com.delivery.eats.order.domain.application.ports.output;

import br.com.delivery.eats.order.domain.core.entity.Customer;
import br.com.delivery.eats.order.domain.core.entity.Restaurant;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository {
    Optional<Customer> findById(UUID uuid);
}
