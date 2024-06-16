package dev.lucas.service;

import java.util.List;
import java.util.UUID;

import dev.lucas.dto.CreateCustomerDTO;
import dev.lucas.entity.CustomerEntity;
import dev.lucas.mapper.CustomerMapper;
import dev.lucas.repository.CustomerRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class CustomerService {

    @Inject
    CustomerRepository customerRepository;

    @Inject
    CustomerMapper customerMapper;

    public List<CustomerEntity> findAllCustomers() {
        return customerRepository.listAll();
    }

    public CustomerEntity create(CreateCustomerDTO customerDTO) {

        CustomerEntity entity = customerRepository.findByEmail(customerDTO.getEmail());
        if (entity != null) {
            throw new BadRequestException("Customer already exists.");
        }
        CustomerEntity customer = customerMapper.toDAO(customerDTO);
        customerRepository.persist(customer);
        return customer;
    }

    public CustomerEntity findByEmail(String email) {
        CustomerEntity entity = customerRepository.findByEmail(email);
        if (entity == null) {
            throw new NotFoundException("Customer not found by email.");
        }
        return entity;
    }

    public CustomerEntity findById(UUID id) {
        CustomerEntity entity = customerRepository.findById(id);
        if (entity == null) {
            throw new NotFoundException("Customer not found by email.");
        }
        return entity;
    }
}
