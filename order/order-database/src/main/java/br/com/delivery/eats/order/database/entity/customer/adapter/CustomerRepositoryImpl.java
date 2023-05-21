package br.com.delivery.eats.order.database.entity.customer.adapter;


import br.com.delivery.eats.order.database.entity.customer.mapper.CustomerDataAccessMapper;
import br.com.delivery.eats.order.database.entity.customer.repository.CustomerJpaRepository;
import br.com.delivery.eats.order.domain.application.ports.output.CustomerRepository;
import br.com.delivery.eats.order.domain.core.entity.Customer;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class CustomerRepositoryImpl implements CustomerRepository {

    private final CustomerJpaRepository customerJpaRepository;
    private final CustomerDataAccessMapper customerDataAccessMapper;

    public CustomerRepositoryImpl(CustomerJpaRepository customerJpaRepository,
                                  CustomerDataAccessMapper customerDataAccessMapper) {
        this.customerJpaRepository = customerJpaRepository;
        this.customerDataAccessMapper = customerDataAccessMapper;
    }


    @Override
    public Optional<Customer> findById(UUID uuid) {
        return customerJpaRepository.findById(uuid).map(customerDataAccessMapper::customerEntityToCustomer);
    }



    @Override
    public Customer save(Customer customer) {
        return customerDataAccessMapper.customerEntityToCustomer(
                customerJpaRepository.save(customerDataAccessMapper.customerToCustomerEntity(customer)));
    }
}
