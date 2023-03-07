package shop.soccerUniform.repository.manager;

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
import shop.soccerUniform.entity.Manager;
import shop.soccerUniform.entity.QManager;
import shop.soccerUniform.entity.dto.ManagerDTO;
import shop.soccerUniform.entity.dto.ManagerSearchForm;
import shop.soccerUniform.entity.enumtype.UserState;

import javax.persistence.EntityManager;
import java.util.List;

import static shop.soccerUniform.entity.QManager.manager;

@Repository
public class ManagerRepositoryImpl implements ManagerQueryRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    @Autowired
    public ManagerRepositoryImpl(EntityManager em) {
        this.em = em;
        queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<ManagerDTO> managers(ManagerSearchForm managerSearchForm, Pageable pageable) {
        List<ManagerDTO> managers = managerList(managerSearchForm, pageable);
        Long count = countManagerList(managerSearchForm, pageable);

        return new PageImpl<>(managers, pageable, count);
    }

    @Override
    public List<Manager> findManagersByState(UserState state) {
        return queryFactory
                .selectFrom(manager)
                .where(manager.state.eq(state))
                .fetch();
    }

    public List<ManagerDTO> managerList(ManagerSearchForm managerSearchForm, Pageable pageable) {
        String searchKey = managerSearchForm.getSearchKey();
        String searchValue = managerSearchForm.getSearchValue();
        UserState state = managerSearchForm.getState();

        return queryFactory
                .select(Projections.fields(ManagerDTO.class,
                        manager.id.as("managerId"),
                        manager.loginId,
                        manager.username,
                        manager.state,
                        manager.ceoName,
                        manager.companyName))
                .from(manager)
                .where(
                        byText(searchKey, searchValue),
                        byState(state))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    public Long countManagerList(ManagerSearchForm managerSearchForm, Pageable pageable) {
        String searchKey = managerSearchForm.getSearchKey();
        String searchValue = managerSearchForm.getSearchValue();
        UserState state = managerSearchForm.getState();

        return queryFactory
                .select(manager.count())
                .from(manager)
                .where(
                        byText(searchKey, searchValue),
                        byState(state))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchOne();
    }

    private BooleanExpression byText(String searchKey, String searchValue) {
        if(StringUtils.hasText(searchKey)) {
            switch (searchKey) {
                case "loginId" :
                    return StringUtils.hasText(searchValue) ? manager.loginId.like("%" + searchValue + "%") : null;

                case "username" :
                    return StringUtils.hasText(searchValue) ? manager.username.like("%" + searchValue + "%") : null;

                case "ceoName" :
                    return StringUtils.hasText(searchValue) ? manager.ceoName.like("%" + searchValue + "%") : null;

                default:
                    return null;
            }
        }
        return null;
    }

    private BooleanExpression byState(UserState state) {
        return state != null ? manager.state.eq(state) : null;
    }
}
