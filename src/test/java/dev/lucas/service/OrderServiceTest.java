package dev.lucas.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mockito;

import dev.lucas.dto.CreateOrderDTO;
import dev.lucas.entity.CustomerEntity;
import dev.lucas.entity.OrderEntity;
import dev.lucas.entity.OrderStatusEnum;
import dev.lucas.entity.PaymentMethodEnum;
import dev.lucas.repository.CustomerRepository;
import dev.lucas.repository.OrderRepository;
import dev.lucas.utils.MockEntities;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;

import org.junit.jupiter.api.Order;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestTransaction
public class OrderServiceTest {
        @Inject
        OrderService orderService;

        @Inject
        OrderRepository orderRepository;

        @Inject
        CustomerRepository customerRepository;

        @BeforeEach
        void setUp() {
                // CustomerRepository mock
                CustomerRepository CustomerRepositoryMock = Mockito.mock(CustomerRepository.class);

                Mockito.when(CustomerRepositoryMock.findByEmail(MockEntities.firstCustomer.getEmail()))
                                .thenReturn(MockEntities.firstCustomer);
                Mockito.when(CustomerRepositoryMock.findByEmail(MockEntities.secondCustomer.getEmail()))
                                .thenReturn(MockEntities.secondCustomer);
                Mockito.when(CustomerRepositoryMock.findByEmail(MockEntities.secondCustomer.getEmail()))
                                .thenReturn(MockEntities.secondCustomer);

                Mockito.when(CustomerRepositoryMock.findById(MockEntities.firstCustomer.getId()))
                                .thenReturn(MockEntities.firstCustomer);
                Mockito.when(CustomerRepositoryMock.findById(MockEntities.secondCustomer.getId()))
                                .thenReturn(MockEntities.secondCustomer);

                Mockito.when(CustomerRepositoryMock.listAll())
                                .thenReturn(List.of(MockEntities.firstCustomer, MockEntities.secondCustomer));

                QuarkusMock.installMockForType(CustomerRepositoryMock, CustomerRepository.class);

                // OrderRepository mock
                OrderRepository orderRepositoryMock = Mockito.mock(OrderRepository.class);

                Mockito.when(orderRepositoryMock.listAll())
                                .thenReturn(List.of(MockEntities.firstOrder));

                Mockito.when(orderRepositoryMock.findById(MockEntities.firstOrder.getId()))
                                .thenReturn(MockEntities.firstOrder);
                Mockito.when(orderRepositoryMock.findByCustomerId(MockEntities.firstCustomer.getId()))
                                .thenReturn(List.of(MockEntities.firstOrder));

                QuarkusMock.installMockForType(orderRepositoryMock, OrderRepository.class);
        }

        @Test
        @Order(1)
        void findAll() {
                assertEquals(orderService.findAll().size(), 1);
        }

        @Test
        @Order(2)
        void findById() {
                UUID id = MockEntities.firstOrder.getId();
                CustomerEntity customer = MockEntities.firstCustomer;

                OrderEntity order = OrderEntity.builder()
                                .id(id)
                                .address("Street 28")
                                .items(List.of("that", "this", "those"))
                                .price(2.50f)
                                .status(OrderStatusEnum.pending)
                                .paymentMethod(PaymentMethodEnum.cash)
                                .customer(customer)
                                .build();

                assertDoesNotThrow(() -> orderService.findById(id));
                assertEquals(orderService.findById(id), order);
        }

        @Test
        @Order(3)
        void findByCustomerId() {
                UUID customerId = MockEntities.firstCustomerId;

                assertDoesNotThrow(() -> orderService.findByCustomerId(customerId));
                assertEquals(1, orderService.findByCustomerId(customerId).size());
                assertEquals(MockEntities.firstOrder, orderService.findByCustomerId(customerId).get(0));
        }

        @Test
        @Order(4)
        void findByCustomerId_NotFound() {
                UUID id = UUID.randomUUID();
                assertThrows(NotFoundException.class, () -> orderService.findByCustomerId(id));
        }

        @Test
        @Transactional
        @Order(5)
        void create() {
                UUID customerId = MockEntities.firstCustomerId;
                CreateOrderDTO createOrderDTO = CreateOrderDTO.builder()
                                .address("Street 28")
                                .items(List.of("that", "this", "those"))
                                .price(2.50f)
                                .status(OrderStatusEnum.pending)
                                .paymentMethod(PaymentMethodEnum.cash)
                                .customerId(customerId)
                                .build();

                assertDoesNotThrow(() -> orderService.create(createOrderDTO));
        }

        @Test
        @Transactional
        @Order(6)
        void create_CustomerNotFound() {
                UUID customerId = UUID.randomUUID();
                CreateOrderDTO customerDTO = CreateOrderDTO.builder()
                                .address("Street 28")
                                .items(List.of("that", "this", "those"))
                                .price(2.50f)
                                .status(OrderStatusEnum.pending)
                                .paymentMethod(PaymentMethodEnum.cash)
                                .customerId(customerId)
                                .build();

                assertThrows(BadRequestException.class, () -> orderService.create(customerDTO));
        }
}
