package dev.lucas.service;

import java.util.List;
import java.util.UUID;

import dev.lucas.dto.CreateOrderDTO;
import dev.lucas.dto.UpdateOrderDTO;
import dev.lucas.entity.CustomerEntity;
import dev.lucas.entity.OrderEntity;
import dev.lucas.mapper.OrderMapper;
import dev.lucas.repository.CustomerRepository;
import dev.lucas.repository.OrderRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class OrderService {

    @Inject
    OrderRepository orderRepository;

    @Inject
    CustomerRepository customerRepository;

    @Inject
    OrderMapper orderMapper;

    public List<OrderEntity> findAll() {
        return orderRepository.listAll();
    }

    public OrderEntity create(CreateOrderDTO orderDto) {
        CustomerEntity entity = customerRepository.findById(orderDto.getCustomerId());
        if (entity == null) {
            throw new BadRequestException("Customer not found while creating order.");
        }

        CustomerEntity customer = customerRepository.findById(orderDto.getCustomerId());
        OrderEntity order = orderMapper.toDAO(orderDto);
        order.setCustomer(customer);
        orderRepository.persist(order);
        return order;
    }

    public OrderEntity update(UUID id, UpdateOrderDTO updateOrderDTO) {
        OrderEntity entity = orderRepository.findById(id);
        if (entity == null) {
            throw new NotFoundException("Order not found.");
        }
        entity.status = updateOrderDTO.getStatus();

        return entity;
    }

    public List<OrderEntity> findByCustomerId(UUID id) {
        CustomerEntity entity = customerRepository.findById(id);
        if (entity == null) {
            throw new NotFoundException("Customer not found.");
        }
        return orderRepository.findByCustomerId(id);
    }

    public OrderEntity findById(UUID id) {
        OrderEntity entity = orderRepository.findById(id);
        if (entity == null) {
            throw new NotFoundException();
        }
        return entity;
    }
}
