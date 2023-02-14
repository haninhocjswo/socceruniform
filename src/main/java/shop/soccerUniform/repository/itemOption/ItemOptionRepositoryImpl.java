package shop.soccerUniform.repository.itemOption;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import shop.soccerUniform.entity.ItemOption;

import javax.persistence.EntityManager;

@Repository
public class ItemOptionRepositoryImpl implements  ItemOptionQueryRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public ItemOptionRepositoryImpl(EntityManager em) {
        this.em = em;
        queryFactory = new JPAQueryFactory(em);
    }

}
