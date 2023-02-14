package shop.soccerUniform.repository.order;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import shop.soccerUniform.entity.Order;

import javax.persistence.EntityManager;

@Repository
public class OrderRepositoryImpl implements OrderQueryRepository{

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    @Autowired
    public OrderRepositoryImpl(EntityManager em) {
        this.em = em;
        queryFactory = new JPAQueryFactory(em);
    }

}
