package shop.soccerUniform.repository.item;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import shop.soccerUniform.entity.Item;

import javax.persistence.EntityManager;

@Repository
public class ItemRepositoryImpl implements  ItemQueryRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    @Autowired
    public ItemRepositoryImpl(EntityManager em) {
        this.em = em;
        queryFactory= new JPAQueryFactory(em);
    }

}
