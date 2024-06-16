package dev.lucas.mapper;

import dev.lucas.dto.CreateCustomerDTO;
import dev.lucas.entity.CustomerEntity;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CustomerMapper {

    public CustomerEntity toDAO(CreateCustomerDTO customerDTO) {
        return CustomerEntity.builder()
                .email(customerDTO.getEmail())
                .name(customerDTO.getName())
                .build();
    };
}