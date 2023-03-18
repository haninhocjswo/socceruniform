package shop.soccerUniform.controller.front.cart;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import shop.soccerUniform.entity.dto.CartForm;
import shop.soccerUniform.entity.dto.MemberForm;
import shop.soccerUniform.service.cart.CartService;
import shop.soccerUniform.service.user.member.MemberService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class FrontCartController {

    private final MemberService memberService;
    private final CartService cartService;

    @GetMapping("/cart/carts")
    public String carts(Principal principal, Model model) {
        List<CartForm> carts = cartService.findCartsByLoginId(principal.getName());
        if(carts == null) {
            carts = new ArrayList<>();
        }
        return "front/cart/carts";
    }
}
