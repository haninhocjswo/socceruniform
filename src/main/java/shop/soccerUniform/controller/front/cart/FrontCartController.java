package shop.soccerUniform.controller.front.cart;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import shop.soccerUniform.entity.dto.MemberForm;
import shop.soccerUniform.service.user.member.MemberService;

import java.security.Principal;

@Slf4j
@Controller
@RequiredArgsConstructor
public class FrontCartController {

    private final MemberService memberService;

    @GetMapping("/cart/carts")
    public String carts(Principal principal, Model model) {
        // TODO 카트 관련 리스트 뽑아내기
        return "front/cart/carts";
    }
}
