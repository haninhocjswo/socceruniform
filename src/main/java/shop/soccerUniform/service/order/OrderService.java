package shop.soccerUniform.service.order;

import shop.soccerUniform.entity.dto.OrderForm;
import shop.soccerUniform.entity.dto.OrderReceiveForm;

import java.util.List;

public interface OrderService {
    public OrderForm receivedItem(OrderReceiveForm orderReceiveForm);
    public OrderForm receivedCart(OrderReceiveForm orderReceiveForm);
}
