package shop.soccerUniform.repository.category;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import shop.soccerUniform.entity.Category;
import shop.soccerUniform.entity.QCategory;
import shop.soccerUniform.entity.dto.CategoryForm;
import shop.soccerUniform.entity.dto.CategorySearchForm;
import shop.soccerUniform.entity.enumtype.CategoryState;

import javax.persistence.EntityManager;

import java.util.List;

import static shop.soccerUniform.entity.QCategory.category;

@Repository
public class CategoryRepositoryImpl implements CategoryQueryRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    @Autowired
    public CategoryRepositoryImpl(EntityManager em) {
        this.em = em;
        queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<CategoryForm> categories(CategorySearchForm categorySearchForm, Pageable pageable) {
        List<CategoryForm> categories = categoryList(categorySearchForm, pageable);
        Long count = countCategoryList(categorySearchForm, pageable);
        return new PageImpl<>(categories, pageable, count);
    }

    @Override
    public List<Category> findByParentDepths(Integer parentDepth) {
        return queryFactory
                .selectFrom(category)
                .where(category.depth.eq(parentDepth))
                .fetch();
    }

    @Override
    public Category findByParentDepth(Long parentId) {
        return queryFactory
                .selectFrom(category)
                .where(category.id.eq(parentId))
                .fetchOne();
    }

    @Override
    public List<Category> findByCategoriesByState(CategoryState categoryState) {
        return queryFactory
                .selectFrom(category)
                .where(category.state.eq(categoryState))
                .fetch();
    }

    public List<CategoryForm> categoryList(CategorySearchForm categorySearchForm, Pageable pageable) {
        Integer depth = categorySearchForm.getDepth();
        String searchKey = categorySearchForm.getSearchKey();
        String searchValue = categorySearchForm.getSearchValue();

        return queryFactory
                .select(Projections.fields(CategoryForm.class,
                        category.id.as("categoryId"),
                        category.name,
                        category.depth,
                        category.state,
                        category.parent.id.as("parentId"),
                        category.parent.name.as("parentName")
                ))
                .from(category)
                .leftJoin(category.parent)
                .where(
                        byText(searchKey, searchValue),
                        byDepth(depth))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    public Long countCategoryList(CategorySearchForm categorySearchForm, Pageable pageable) {
        Integer depth = categorySearchForm.getDepth();
        String searchKey = categorySearchForm.getSearchKey();
        String searchValue = categorySearchForm.getSearchValue();

        return queryFactory
                .select(category.count())
                .from(category)
                .where(
                        byText(searchKey, searchValue),
                        byDepth(depth))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchOne();
    }

    public BooleanExpression byText(String searchKey, String searchValue) {
        if(StringUtils.hasText(searchKey)) {
            switch (searchKey) {
                case "name" :
                    return StringUtils.hasText(searchValue) ? category.name.like("%" + searchValue + "%") : null;

                case "parentName" :
                    return StringUtils.hasText(searchValue) ? category.parent.name.like("%" + searchValue + "%") : null;

                default:
                    return null;
            }
        }

        return null;
    }

    public BooleanExpression byDepth(Integer depth) {
        return depth != null ? category.depth.eq(depth) : null;
    }
}
