package shop.soccerUniform.repository.itemOptionStock;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import shop.soccerUniform.entity.ItemOptionStock;
import shop.soccerUniform.entity.ItemOptionValue;
import shop.soccerUniform.entity.QItemOptionStock;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static shop.soccerUniform.entity.QItemOptionStock.itemOptionStock;

@Slf4j
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
    public Optional<ItemOptionStock> findByFirstOptionValueAndSecondOptionValue(ItemOptionValue firstItemOptionValue, ItemOptionValue secondItemOptionValue) {
        ItemOptionStock optionStock = queryFactory
                .selectFrom(itemOptionStock)
                .where(itemOptionStock.firstOptionValue.id.eq(firstItemOptionValue.getId()),
                        bySecondItemOptionValueId(secondItemOptionValue))
                .fetchOne();
        return Optional.ofNullable(optionStock);
    }

    @Override
    public void deletedByItemId(Long itemId) {
        queryFactory
                .delete(itemOptionStock)
                .where(itemOptionStock.item.id.eq(itemId))
                .execute();
    }

    public BooleanExpression bySecondItemOptionValueId(ItemOptionValue secondItemOptionValue) {
        if(secondItemOptionValue == null) return null;
        return secondItemOptionValue.getId() != null ? itemOptionStock.secondOptionValue.id.eq(secondItemOptionValue.getId()) : null;
    }
}
