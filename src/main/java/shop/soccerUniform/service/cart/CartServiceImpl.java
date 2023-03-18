package shop.soccerUniform.service.cart;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.soccerUniform.entity.Cart;
import shop.soccerUniform.entity.Member;
import shop.soccerUniform.entity.dto.CartForm;
import shop.soccerUniform.repository.cart.CartRepository;
import shop.soccerUniform.repository.member.MemberRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final MemberRepository memberRepository;

    @Override
    public List<CartForm> findCartsByLoginId(String loginId) {
        Optional<Member> optionalMember = memberRepository.findByLoginId(loginId);
        List<CartForm> emptyCarts = new ArrayList<>();
        if(optionalMember.isEmpty()) {
            return emptyCarts;
        }

        Member member = optionalMember.get();
        List<Cart> findCarts = cartRepository.findByMemberId(member.getId());
        if(findCarts.size() == 0) {
            return emptyCarts;
        }

        List<CartForm> carts = new ArrayList<>();
        for (Cart cart : findCarts) {
            CartForm cartForm = new CartForm();
            cartForm.setCartId(cart.getId());
            cartForm.setItem(cart.getItem());
            cartForm.setMemberId(member.getId());

            carts.add(cartForm);
        }

        return null;
    }
}
