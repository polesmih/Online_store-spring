package web.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.*;
import web.converters.OrderConverter;
import web.dto.OrderDetailsDto;
import web.dto.OrderDto;
import web.services.OrderService;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
        private final OrderService orderService;
        private final OrderConverter orderConverter;

    @Qualifier(value = "KafkaTest")
    @Autowired
    private KafkaTemplate<String, OrderDetailsDto> kafkaTemplate;

    @PostMapping("/{cartName}")
    public void createOrder(@RequestHeader String username, @RequestBody OrderDetailsDto orderDetailsDto, @PathVariable String cartName){
       String key = username + "/" + cartName;
       kafkaTemplate.send("Orders", key, orderDetailsDto);

    }
    @GetMapping
    public List<OrderDto> getCurrenUrders(@RequestHeader String username){
        return orderService.findOrdersByUsername(username).stream()
                .map(orderConverter::entityToDto).collect(Collectors.toList());
    }


}
