package dev.lucas.service;

import java.util.List;
import java.util.UUID;

import dev.lucas.dto.CreateOrderDTO;
import dev.lucas.entity.Customer;
import dev.lucas.entity.Order;
import dev.lucas.entity.OrderStatusType;
import dev.lucas.mapper.OrderMapper;
import dev.lucas.repository.CustomerRepository;
import dev.lucas.repository.OrderRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class OrderService {

    @Inject
    OrderRepository orderRepository;

    @Inject
    CustomerRepository customerRepository;

    @Inject
    OrderMapper orderMapper;

    public List<Order> findAll() {
        return orderRepository.listAll();
    }

    public Order create(CreateOrderDTO orderDto) {
        Customer customer = customerRepository.findById(orderDto.getCustomerId());
        Order order = orderMapper.toDAO(orderDto);
        order.setCustomer(customer);
        orderRepository.persist(order);
        return order;
    }

    @Transactional
    public Order update(UUID id, OrderStatusType status) {
        Order entity = orderRepository.findById(id);
        if (entity == null) {
            throw new NotFoundException();
        }
        entity.status = status;

        return entity;
    }

    public List<Order> findByCustomerId(UUID id) {
        return orderRepository.findByCustomerId(id);
    }

    public Order findById(UUID id) {
        Order entity = orderRepository.findById(id);
        if (entity == null) {
            throw new NotFoundException();
        }
        return entity;
    }
}
