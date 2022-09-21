package com.geekbrains.spring.web.cart.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDto {

    private Long productId;
    @Schema(description = "Название продукта")
    private String title;
    @Schema(description = "Количество")
    private int quantity;
    @Schema(description = "Цена за еденицу продукта")
    private int pricePerProduct;
    @Schema(description = "Сумма")
    private int price;

    public OrderItemDto(ProductDto product){
        this.productId = product.getId();
        this.title = product.getTitle();
        this.quantity = 1;
        this.pricePerProduct = product.getPrice();
        this.price = product.getPrice();
    }

    public void changeQuantity(int delta){
        this.quantity += delta;
        this.price = this.quantity * this.pricePerProduct;
    }

}
