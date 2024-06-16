package dev.lucas.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mockito;

import dev.lucas.dto.CreateCustomerDTO;
import dev.lucas.entity.CustomerEntity;
import dev.lucas.repository.CustomerRepository;
import dev.lucas.utils.MockEntities;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestTransaction
public class CustomerServiceTest {
    @Inject
    CustomerService customerService;

    @Inject
    CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        CustomerRepository mock = Mockito.mock(CustomerRepository.class);
        Mockito.when(mock.findByEmail(MockEntities.firstCustomer.email))
                .thenReturn(MockEntities.firstCustomer);
        Mockito.when(mock.findByEmail(MockEntities.secondCustomer.email))
                .thenReturn(MockEntities.secondCustomer);
        Mockito.when(mock.findByEmail(MockEntities.secondCustomer.email))
                .thenReturn(MockEntities.secondCustomer);

        Mockito.when(mock.findById(MockEntities.firstCustomer.id))
                .thenReturn(MockEntities.firstCustomer);
        Mockito.when(mock.findById(MockEntities.secondCustomer.id))
                .thenReturn(MockEntities.secondCustomer);

        Mockito.when(mock.listAll())
                .thenReturn(List.of(MockEntities.firstCustomer, MockEntities.secondCustomer));

        QuarkusMock.installMockForType(mock, CustomerRepository.class);
    }

    @Test
    @Order(1)
    void findAllCustomers() {
        assertEquals(2, customerService.findAllCustomers().size());
    }

    @Test
    @Order(2)
    void findById() {
        UUID id = MockEntities.firstCustomerId;
        CustomerEntity customer = MockEntities.firstCustomer;

        assertDoesNotThrow(() -> customerService.findById(id));
        assertEquals(customer, customerService.findById(id));
    }

    @Test
    @Order(3)
    void findById_NotFound() {
        UUID id = UUID.randomUUID();
        assertThrows(NotFoundException.class, () -> customerService.findById(id));
    }

    @Test
    @Order(4)
    void findByEmail() {
        String validEmail = MockEntities.firstCustomer.getEmail();
        CustomerEntity customer = MockEntities.firstCustomer;

        assertDoesNotThrow(() -> customerService.findByEmail(validEmail));
        assertEquals(customer, customerService.findByEmail(validEmail));
    }

    @Test
    @Order(5)
    void findByEmail_NotFound() {
        String invalidEmail = "emailNotFound@email.com";
        assertThrows(NotFoundException.class, () -> customerService.findByEmail(invalidEmail));
    }

    @Test
    @Order(6)
    @Transactional
    void create() {
        CreateCustomerDTO customerDTO = CreateCustomerDTO
                .builder().name("Third User").email("third_user@email.com").build();
        assertEquals("third_user@email.com", customerService.create(customerDTO).getEmail());
    }

    @Test
    @Order(7)
    @Transactional
    void create_AlreadyExists() {
        CustomerEntity customerExistent = MockEntities.firstCustomer;
        CreateCustomerDTO customerDTO = CreateCustomerDTO
                .builder().name(customerExistent.getName()).email(customerExistent.getEmail()).build();

        assertThrows(BadRequestException.class, () -> customerService.create(customerDTO));
    }
}