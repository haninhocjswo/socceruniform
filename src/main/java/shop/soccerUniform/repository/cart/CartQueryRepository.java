package shop.soccerUniform.repository.cart;

import shop.soccerUniform.entity.Cart;
import shop.soccerUniform.entity.ItemOptionStock;

import java.util.List;

public interface CartQueryRepository {
    public List<Cart> findByMemberId(Long memberId);
    public Long cartFindOverlap(Long memberId, Long itemId, Long itemOptionStockId);
}
