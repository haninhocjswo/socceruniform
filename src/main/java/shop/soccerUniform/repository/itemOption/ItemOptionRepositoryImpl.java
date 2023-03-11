package shop.soccerUniform.repository.itemOption;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import shop.soccerUniform.entity.ItemOption;
import shop.soccerUniform.entity.QItemOption;

import javax.persistence.EntityManager;
import java.util.List;

import static shop.soccerUniform.entity.QItemOption.itemOption;

@Repository
public class ItemOptionRepositoryImpl implements  ItemOptionQueryRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public ItemOptionRepositoryImpl(EntityManager em) {
        this.em = em;
        queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<ItemOption> findByItemId(Long itemId) {
        return queryFactory
                .selectFrom(itemOption)
                .where(itemOption.item.id.eq(itemId))
                .orderBy(itemOption.optionSort.asc())
                .fetch();
    }
}
