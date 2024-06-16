package dev.lucas.utils;

import java.util.List;
import java.util.UUID;

import dev.lucas.entity.CustomerEntity;
import dev.lucas.entity.OrderEntity;
import dev.lucas.entity.OrderStatusEnum;
import dev.lucas.entity.PaymentMethodEnum;

public record MockEntities() {
        public static UUID firstCustomerId = UUID.fromString("afff3cb4-c290-42c5-84f4-56ab644dcd1a");
        public static CustomerEntity firstCustomer = CustomerEntity.builder().id(firstCustomerId).name("First User")
                        .email("first_user@email.com").build();

        public static UUID secondCustomerId = UUID.fromString("64b0834e-f350-4976-8a98-48ec117d72b1");
        public static CustomerEntity secondCustomer = CustomerEntity.builder().id(secondCustomerId).name("Second User")
                        .email("second_user@email.com").build();

        public static OrderEntity firstOrder = OrderEntity.builder()
                        .id(UUID.fromString("3d9d6c17-39a0-4487-9b7c-c24ea6626271"))
                        .address("Street 28")
                        .items(List.of("that", "this", "those"))
                        .price(2.50f)
                        .status(OrderStatusEnum.pending)
                        .paymentMethod(PaymentMethodEnum.cash)
                        .customer(firstCustomer)
                        .build();
}
