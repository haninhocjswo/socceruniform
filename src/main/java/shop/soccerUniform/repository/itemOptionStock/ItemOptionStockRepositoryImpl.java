package shop.soccerUniform.repository.itemOptionStock;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import shop.soccerUniform.entity.ItemOptionStock;
import shop.soccerUniform.entity.QItemOptionStock;

import javax.persistence.EntityManager;
import java.util.List;

import static shop.soccerUniform.entity.QItemOptionStock.itemOptionStock;

@Repository
public class ItemOptionStockRepositoryImpl implements ItemOptionStockQueryRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    @Autowired
    public ItemOptionStockRepositoryImpl(EntityManager em) {
        this.em = em;
        queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<ItemOptionStock> findByItemId(Long itemId) {
        return queryFactory
                .selectFrom(itemOptionStock)
                .where(itemOptionStock.item.id.eq(itemId))
                .orderBy(itemOptionStock.sort.asc())
                .fetch();
    }

    @Override
    public void deletedByItemId(Long itemId) {
        queryFactory
                .delete(itemOptionStock)
                .where(itemOptionStock.item.id.eq(itemId))
                .execute();
    }
}
