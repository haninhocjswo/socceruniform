package shop.soccerUniform.repository.cart;

import shop.soccerUniform.entity.Cart;

import java.util.List;

public interface CartQueryRepository {
    public List<Cart> findByMemberId(Long memberId);
}
