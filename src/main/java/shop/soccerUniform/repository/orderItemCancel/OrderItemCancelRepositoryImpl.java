package shop.soccerUniform.repository.orderItemCancel;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import shop.soccerUniform.entity.OrderItemCancel;

import javax.persistence.EntityManager;

@Repository
public class OrderItemCancelRepositoryImpl implements OrderItemCancelQueryRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    @Autowired
    public OrderItemCancelRepositoryImpl(EntityManager em) {
        this.em = em;
        queryFactory = new JPAQueryFactory(em);
    }

}
