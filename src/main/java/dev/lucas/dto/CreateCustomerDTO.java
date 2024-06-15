package dev.lucas.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateCustomerDTO {
    private String email;
    private String name;
}
