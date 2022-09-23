package com.geekbrains.spring.web.order.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class OrderDto {
    private Long id;
    @Schema(description = "Имя пользователя")
    private String username;
    @Schema(description = "Список продуктов")
    private List<OrderItemDto> itemDtoList;
    @Schema(description = "Сумма заказа")
    private Integer totalPrice;
    @Schema(description = "Адрес")
    private String address;
    @Schema(description = "Телефон")
    private String phone;
}
