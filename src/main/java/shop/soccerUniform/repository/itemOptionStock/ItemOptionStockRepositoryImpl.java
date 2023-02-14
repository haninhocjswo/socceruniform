package shop.soccerUniform.repository.itemOptionStock;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import shop.soccerUniform.entity.ItemOptionStock;

import javax.persistence.EntityManager;

@Repository
public class ItemOptionStockRepositoryImpl implements ItemOptionStockQueryRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    @Autowired
    public ItemOptionStockRepositoryImpl(EntityManager em) {
        this.em = em;
        queryFactory = new JPAQueryFactory(em);
    }

}
