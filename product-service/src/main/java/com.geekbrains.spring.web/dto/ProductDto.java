package com.geekbrains.spring.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ProductDto {
    private Long id;
    @Schema(description = "Название продукта")
    private String title;
    @Schema(description = "Цена продукта")
    private Integer price;
}
