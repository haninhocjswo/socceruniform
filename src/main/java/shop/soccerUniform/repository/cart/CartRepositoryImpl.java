package shop.soccerUniform.repository.cart;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import shop.soccerUniform.entity.Cart;

import javax.persistence.EntityManager;

@Repository
public class CartRepositoryImpl implements CartQueryRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    @Autowired
    public CartRepositoryImpl(EntityManager em) {
        this.em = em;
        queryFactory = new JPAQueryFactory(em);
    }

}
