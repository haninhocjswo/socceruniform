package shop.soccerUniform.repository.item;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import shop.soccerUniform.entity.*;
import shop.soccerUniform.entity.dto.ItemForm;
import shop.soccerUniform.entity.dto.ItemSearchForm;
import shop.soccerUniform.entity.enumtype.ItemState;
import shop.soccerUniform.entity.enumtype.OptionType;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static shop.soccerUniform.entity.QItem.item;

@Slf4j
@Repository
public class ItemRepositoryImpl implements  ItemQueryRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    @Autowired
    public ItemRepositoryImpl(EntityManager em) {
        this.em = em;
        queryFactory= new JPAQueryFactory(em);
    }

    @Override
    public Page<ItemForm> items(ItemSearchForm itemSearchForm, Pageable pageable) {
        List<ItemForm> items = itemList(itemSearchForm, pageable);
        Long count = countItem(itemSearchForm, pageable);
        return new PageImpl<>(items, pageable, count);
    }

    public List<ItemForm> itemList(ItemSearchForm itemSearchForm, Pageable pageable) {
        String searchKey = itemSearchForm.getSearchKey();
        String searchValue = itemSearchForm.getSearchValue();

        log.info("pageable.getPageNumber()={}", pageable.getPageNumber());

        return queryFactory
                .select(Projections.fields(ItemForm.class,
                        item.id.as("itemId"),
                        item.manager,
                        item.category,
                        item.manufacturer,
                        item.origin,
                        item.description,
                        item.optionType,
                        item.price,
                        item.state))
                .from(item)
                .where(byText(searchKey, searchValue))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    public Long countItem(ItemSearchForm itemSearchForm, Pageable pageable) {
        String searchKey = itemSearchForm.getSearchKey();
        String searchValue = itemSearchForm.getSearchValue();

        return queryFactory
                .select(item.count())
                .from(item)
                .where(byText(searchKey, searchValue))
                .fetchOne();
    }

    public BooleanExpression byText(String searchKey, String searchValue) {
        if(StringUtils.hasText(searchKey)) {

        }
        return null;
    }
}
