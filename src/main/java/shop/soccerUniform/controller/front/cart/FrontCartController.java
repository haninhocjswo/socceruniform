package shop.soccerUniform.controller.front.cart;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import shop.soccerUniform.entity.dto.CartForm;
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
    private final CartService cartService;
    private final MemberService memberService;

    @GetMapping("/cart/carts")
    public String carts(Principal principal, Model model) {
        List<CartForm> carts = cartService.findCartsByLoginId(principal.getName());
        model.addAttribute("carts", carts);

        MemberForm member = memberService.memberFindByLoginId(principal.getName());
        model.addAttribute("memberId", member.getMemberId());

        return "front/cart/carts";
    }

    @PostMapping("/cart/save")
    public ResponseEntity<Map<String, Object>> saveCart(CartForm cartForm, Model model) {
        Map<String, Object> ajaxMap = new HashMap<>();

        boolean overlapCheck = cartService.overlapCheck(cartForm);
        if(!overlapCheck) {
            ajaxMap.put("result", overlapCheck);
            ajaxMap.put("message", "장바구니에 이미 존재하는 제품입니다.");
        } else {
            boolean result = cartService.saveCart(cartForm);
            ajaxMap.put("result", result);
            if(!result) {
                ajaxMap.put("message", "장바구니 담기에 실패하였습니다.");
            }
        }

        ResponseEntity<Map<String, Object>> responseEntity = new ResponseEntity<>(ajaxMap, HttpStatus.OK);

        return responseEntity;
    }

    @PostMapping("/cart/edit")
    public ResponseEntity<Map<String, Object>> editCart(@RequestParam Long cartId, @RequestParam Integer stock) {
        Map<String, Object> ajaxMap = new HashMap<>();
        boolean result = cartService.editCart(cartId, stock);
        ajaxMap.put("result", result);
        if(!result) {
            ajaxMap.put("message", "수량을 수정이 실패하였습니다.");
        }
        ResponseEntity<Map<String, Object>> responseEntity = new ResponseEntity<>(ajaxMap, HttpStatus.OK);
        return responseEntity;
    }

    @PostMapping("/cart/delete")
    public ResponseEntity<Map<String, Object>> delCart(@RequestParam Long cartId) {
        Map<String, Object> ajaxMap = new HashMap<>();
        boolean result = cartService.delCart(cartId);
        ajaxMap.put("result", result);
        if(!result) {
            ajaxMap.put("message", "삭제에 실패하였습니다.");
        }
        ResponseEntity<Map<String, Object>> responseEntity = new ResponseEntity<>(ajaxMap, HttpStatus.OK);
        return responseEntity;
    }
}
