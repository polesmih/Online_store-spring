package web.converters;

import org.springframework.stereotype.Component;
import web.dto.OrderItemDto;
import web.entities.OrderItem;

@Component
public class OrderItemConverter {

    public OrderItemDto entityToDto(OrderItem orderItem){
        return new OrderItemDto(orderItem.getProductId(), orderItem.getProductTitle(),
                orderItem.getQuantity(), orderItem.getPricePerProduct(), orderItem.getPrice());
    }
}
