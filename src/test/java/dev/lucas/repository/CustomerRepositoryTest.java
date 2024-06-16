package dev.lucas.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import dev.lucas.entity.CustomerEntity;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CustomerRepositoryTest {
    @Inject
    CustomerRepository customerRepository;

    @Test
    @Order(1)
    void findByEmail() {
        String validEmail = "first_user@email.com";
        UUID id = UUID.fromString("afff3cb4-c290-42c5-84f4-56ab644dcd1a");
        CustomerEntity customer = CustomerEntity.builder().id(id).name("First User").email(validEmail).build();

        assertEquals(customer, customerRepository.findByEmail("first_user@email.com"));
    }
}
