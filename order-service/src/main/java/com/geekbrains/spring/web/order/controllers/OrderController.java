package com.geekbrains.spring.web.order.controllers;

import com.geekbrains.spring.web.order.converters.OrderConverter;
import com.geekbrains.spring.web.order.dto.OrderDetailsDto;
import com.geekbrains.spring.web.order.dto.OrderDto;
import com.geekbrains.spring.web.order.services.OrderService;
import com.geekbrains.spring.web.order.services.QiwiService;
import com.qiwi.billpayments.sdk.client.BillPaymentClient;
import com.qiwi.billpayments.sdk.model.out.BillResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@Tag(name = "Заказы", description = "Контроллер заказов")
public class OrderController {
        private final OrderService orderService;
        private final OrderConverter orderConverter;
        private final BillPaymentClient billPaymentClient;
        private final QiwiService qiwiService;

        @Qualifier(value = "KafkaTest")
        @Autowired
        private KafkaTemplate<String, OrderDetailsDto> kafkaTemplate;

    @Operation(description = "Создание заказа из текущей корзины")
    @PostMapping("/{cartName}")
    public void createOrder(@RequestHeader String username, @RequestBody OrderDetailsDto orderDetailsDto, @PathVariable String cartName){

        System.out.println("-----------------ORDER SERVICE: createOrder-----------------");
        String key = username + "/" + cartName;
        kafkaTemplate.send("Orders", key, orderDetailsDto);
    }

    @Operation(description = "Получение заказов по имени пользователя")
    @GetMapping
    public List<OrderDto> getCurrentOrders(@RequestHeader String username){
        System.out.println("-----------------ORDER SERVICE: getCurrentOrders-----------------");
        return orderService.findOrdersByUsername(username).stream()
                .map(orderConverter::entityToDto).collect(Collectors.toList());
    }
    @Operation(description = "Получение заказа по id")
    @GetMapping("/{id}")
    public OrderDto getOrderById (@PathVariable Long id){
        System.out.println("-----------------ORDER SERVICE: getOrderById-----------------");
        return orderConverter.entityToDto(orderService.findOrderById(id));
    }

    @Operation(description = "Создание оплаты через QIWI")
    @PutMapping ("/qiwi/{orderId}")
    public void createBill(@PathVariable Long orderId) throws IOException, URISyntaxException {
        System.out.println("-----------------ORDER SERVICE: createBill-----------------");
        BillResponse response = billPaymentClient.createBill(qiwiService.createBill(orderId));
        System.out.println(response.getStatus());
    }

    @Operation(description = "Проверка статуса оплаты")
    @GetMapping ("/status/{orderId}")
    public String checkOrderStatus(@PathVariable Long orderId) throws IOException, URISyntaxException {
        System.out.println("-----------------ORDER SERVICE: checkOrderStatus-----------------");
        System.out.println("Статус заказа № " + orderId + " - " + orderService.findOrderById(orderId).getStatus());
       return orderService.findOrderById(orderId).getStatus();
    }

}
