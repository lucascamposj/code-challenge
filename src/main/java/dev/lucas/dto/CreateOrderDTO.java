package dev.lucas.dto;

import java.util.List;
import java.util.UUID;

import dev.lucas.entity.OrderStatusType;
import dev.lucas.entity.PaymentMethodType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderDTO {
    private float price;
    private List<String> items;
    private String address;
    private PaymentMethodType paymentMethod;
    private OrderStatusType status;
    private UUID customerId;
}
