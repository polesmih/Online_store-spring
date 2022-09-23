package com.geekbrains.spring.web.order.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderDetailsDto {
    @Schema(description = "Адрес")
    private String address;
    @Schema(description = "Телефон")
    private String phone;
}
