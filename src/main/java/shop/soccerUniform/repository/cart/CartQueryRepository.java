package shop.soccerUniform.repository.cart;

import shop.soccerUniform.entity.Cart;
import shop.soccerUniform.entity.ItemOptionStock;

import java.util.List;
import java.util.Set;

public interface CartQueryRepository {
    public List<Cart> findByMemberId(Long memberId);
    public Long cartFindOverlap(Long memberId, Long itemId, Long itemOptionStockId);
    public List<Cart> findInCartId(Set<Long> cartIds);
}
