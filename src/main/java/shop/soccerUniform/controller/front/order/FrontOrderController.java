package shop.soccerUniform.controller.front.order;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import shop.soccerUniform.entity.dto.ItemForm;
import shop.soccerUniform.entity.dto.OrderForm;
import shop.soccerUniform.entity.dto.OrderReceiveForm;
import shop.soccerUniform.service.order.OrderService;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class FrontOrderController {

    private final OrderService orderService;

    @GetMapping("/order/order")
    public String orderPage(@ModelAttribute OrderReceiveForm orderReceiveForm, Model model) {
        // TODO 주문받는 부분 다시
        OrderForm orderForm = new OrderForm();
        if(orderReceiveForm.getReq().equals("item")) orderForm = orderService.receivedItem(orderReceiveForm);
        if(orderReceiveForm.getReq().equals("cart")) orderForm = orderService.receivedCart(orderReceiveForm);
        if(!orderForm.isResultFlag()) return "redirect:/";

        model.addAttribute(orderForm);

        return "front/order/orderForm";
    }
}
