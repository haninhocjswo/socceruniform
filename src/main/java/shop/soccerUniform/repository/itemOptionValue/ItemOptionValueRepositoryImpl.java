package shop.soccerUniform.repository.itemOptionValue;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import shop.soccerUniform.entity.ItemOptionValue;
import shop.soccerUniform.entity.QItemOptionValue;

import javax.persistence.EntityManager;
import java.util.List;

import static shop.soccerUniform.entity.QItemOptionValue.itemOptionValue;

@Repository
public class ItemOptionValueRepositoryImpl implements ItemOptionValueQueryRepository {

    private final EntityManager em;

    private final JPAQueryFactory queryFactory;

    public ItemOptionValueRepositoryImpl(EntityManager em) {
        this.em = em;
        queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<ItemOptionValue> findByItemOptionId(Long itemOptionId) {
        return queryFactory
                .selectFrom(itemOptionValue)
                .where(itemOptionValue.itemOption.id.eq(itemOptionId))
                .orderBy(itemOptionValue.optionValueSort.asc())
                .fetch();
    }

    @Override
    public void deletedByItemId(Long itemId) {
        queryFactory
                .delete(itemOptionValue)
                .where(itemOptionValue.item.id.eq(itemId))
                .execute();
    }
}
