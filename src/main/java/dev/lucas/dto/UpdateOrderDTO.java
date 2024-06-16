package dev.lucas.dto;

import dev.lucas.entity.OrderStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateOrderDTO {
    private OrderStatusEnum status;
}
