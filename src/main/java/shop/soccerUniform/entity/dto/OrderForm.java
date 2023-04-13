package shop.soccerUniform.entity.dto;

import lombok.Data;
import shop.soccerUniform.entity.*;
import shop.soccerUniform.entity.enumtype.OrderState;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class OrderForm {

    private Long orderId;
    private String orderNum;
    private OrderState state;
    private List<OrderItemForm> orderItemForms;
    private Integer totalEventSalePrice;
    private Integer totalPointSalePrice;
    private Integer totalSalePrice;
    private Integer totalPrice;
    private Delivery delivery;
    private Member member;

}
