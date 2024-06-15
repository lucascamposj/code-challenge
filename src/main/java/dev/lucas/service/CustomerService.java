package dev.lucas.service;

import java.util.List;
import java.util.UUID;

import dev.lucas.entity.Customer;
import dev.lucas.repository.CustomerRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class CustomerService {

    @Inject
    CustomerRepository customerRepository;

    public List<Customer> findAllCustomers() {
        return customerRepository.listAll();
    }

    public Customer create(Customer customer) {

        Customer entity = customerRepository.findByEmail(customer.email);
        if (entity != null) {
            throw new BadRequestException("Customer already exists");
        }

        customerRepository.persist(customer);
        return customer;
    }

    public Customer findByEmail(String email) {
        Customer entity = customerRepository.findByEmail(email);
        if (entity == null) {
            throw new NotFoundException();
        }
        return entity;
    }

    public Customer findById(UUID id) {
        Customer entity = customerRepository.findById(id);
        if (entity == null) {
            throw new NotFoundException();
        }
        return entity;
    }
}
