package dev.lucas.dto;

import java.util.List;
import java.util.UUID;

import dev.lucas.entity.OrderStatusEnum;
import dev.lucas.entity.PaymentMethodEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateOrderDTO {
    private float price;
    private List<String> items;
    private String address;
    private PaymentMethodEnum paymentMethod;
    private OrderStatusEnum status;
    private UUID customerId;
}
