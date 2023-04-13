package shop.soccerUniform.entity.dto;

import lombok.Data;
import shop.soccerUniform.entity.Event;
import shop.soccerUniform.entity.Item;
import shop.soccerUniform.entity.ItemOptionStock;
import shop.soccerUniform.entity.Point;
import shop.soccerUniform.entity.enumtype.OrderState;

import java.time.LocalDateTime;

@Data
public class OrderItemForm {

    private Long orderItemId;

    private Long orderId;

    private OrderState orderItemState;

    private Integer quantity;

    private Integer price;

    private Integer finalPrice;

    private Integer totalSalePrice;

    private Integer eventSalePrice;

    private Integer pointSalePrice;

    private Event event;

    private Point point;

    private Item item;

    private ItemOptionStock itemOptionStock;
}
