package dev.lucas.mapper;

import dev.lucas.dto.CreateOrderDTO;
import dev.lucas.entity.Order;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class OrderMapper {

    public Order toDAO(CreateOrderDTO orderDTO) {
        return Order.builder()
                .items(orderDTO.getItems())
                .price(orderDTO.getPrice())
                .status(orderDTO.getStatus())
                .paymentMethod(orderDTO.getPaymentMethod())
                .address(orderDTO.getAddress())
                .build();
    };
}