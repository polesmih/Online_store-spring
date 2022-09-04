package web.controllers;

import lombok.RequiredArgsConstructor;
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


    @PostMapping("/{cartName}")
    public void createOrder(@RequestHeader String username, @RequestBody OrderDetailsDto orderDetailsDto, @PathVariable String cartName){
       orderService.createOrder(username, orderDetailsDto, cartName);
    }

    @GetMapping
    public List<OrderDto> getCurrenUrders(@RequestHeader String username){
        return orderService.findOrdersByUsername(username).stream()
                .map(orderConverter::entityToDto).collect(Collectors.toList());
    }


}
