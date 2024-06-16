package dev.lucas.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import java.util.UUID;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import dev.lucas.entity.OrderEntity;
import dev.lucas.utils.MockEntities;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrderRepositoryTest {
    @Inject
    OrderRepository orderRepository;

    @Test
    void findByCustomerId() {
        UUID customerId = MockEntities.firstCustomer.getId();
        UUID orderId = MockEntities.firstOrder.getId();

        assertEquals(1, orderRepository.findByCustomerId(customerId).size());
        assertInstanceOf(OrderEntity.class, orderRepository.findByCustomerId(customerId).get(0));
        assertEquals(orderId, orderRepository.findByCustomerId(customerId).get(0).getId());
        assertEquals(customerId, orderRepository.findByCustomerId(customerId).get(0).customer.getId());
    }
}