package shop.soccerUniform.controller.front.cart;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import shop.soccerUniform.entity.dto.CartForm;
import shop.soccerUniform.entity.dto.FrontItemForm;
import shop.soccerUniform.entity.dto.MemberForm;
import shop.soccerUniform.service.cart.CartService;
import shop.soccerUniform.service.user.member.MemberService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @PostMapping("/cart/save")
    public ResponseEntity<Map<String, Object>> saveCart(@RequestBody CartForm cartForm, Model model) {
        Map<String, Object> ajaxMap = new HashMap<>();
        // TODO 장바구니 추가
        log.info("cartForm={}", cartForm);
        log.info("saveCart~~");
        ajaxMap.put("result", true);
        ResponseEntity<Map<String, Object>> responseEntity = new ResponseEntity<>(ajaxMap, HttpStatus.OK);
        return responseEntity;
    }
}
