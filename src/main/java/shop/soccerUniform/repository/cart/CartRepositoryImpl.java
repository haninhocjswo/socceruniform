package shop.soccerUniform.repository.cart;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import shop.soccerUniform.entity.Cart;
import shop.soccerUniform.entity.QCart;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Set;

import static shop.soccerUniform.entity.QCart.cart;

@Repository
public class CartRepositoryImpl implements CartQueryRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    @Autowired
    public CartRepositoryImpl(EntityManager em) {
        this.em = em;
        queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Cart> findByMemberId(Long memberId) {
        return queryFactory
                .selectFrom(cart)
                .join(cart.item).fetchJoin()
                .orderBy(cart.item.name.asc())
                .fetch();
    }

    @Override
    public Long cartFindOverlap(Long memberId, Long itemId, Long itemOptionStockId) {
        return queryFactory
                .select(cart.count())
                .from(cart)
                .where(
                        cart.member.id.eq(memberId),
                        cart.item.id.eq(itemId),
                        cart.itemOptionStock.id.eq(itemOptionStockId))
                .fetchOne();
    }

    @Override
    public List<Cart> findInCartId(Set<Long> cartIds) {
        return queryFactory
                .selectFrom(cart)
                .where(cart.id.in(cartIds))
                .fetch();
    }
}
