package dev.lucas.mapper;

import dev.lucas.dto.CreateOrderDTO;
import dev.lucas.entity.OrderEntity;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class OrderMapper {

    public OrderEntity toDAO(CreateOrderDTO orderDTO) {
        return OrderEntity.builder()
                .items(orderDTO.getItems())
                .price(orderDTO.getPrice())
                .status(orderDTO.getStatus())
                .paymentMethod(orderDTO.getPaymentMethod())
                .address(orderDTO.getAddress())
                .build();
    };
}