package br.com.delivery.eats.order.database.entity.customer.mapper;


import br.com.delivery.eats.common.domain.valueobject.CustomerId;
import br.com.delivery.eats.order.database.entity.customer.entity.CustomerEntity;
import br.com.delivery.eats.order.domain.core.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerDataAccessMapper {

    public Customer customerEntityToCustomer(CustomerEntity customerEntity) {
        return new Customer(new CustomerId(customerEntity.getId()));
    }

    public CustomerEntity customerToCustomerEntity(Customer customer) {
        return CustomerEntity.builder()
                .id(customer.getId().getValue())
                .username(customer.getUsername())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .build();
    }
}
