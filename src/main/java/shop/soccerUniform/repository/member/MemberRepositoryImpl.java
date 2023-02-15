package shop.soccerUniform.repository.member;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.apache.el.lang.ExpressionBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import shop.soccerUniform.entity.Member;
import shop.soccerUniform.entity.QMember;
import shop.soccerUniform.entity.dto.MemberSearchForm;
import shop.soccerUniform.entity.dto.MembersDTO;
import shop.soccerUniform.entity.enumtype.Grade;
import shop.soccerUniform.entity.enumtype.UserState;

import javax.persistence.EntityManager;
import java.util.List;

import static shop.soccerUniform.entity.QMember.member;

@Repository
public class MemberRepositoryImpl implements  MemberQueryRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    @Autowired
    public MemberRepositoryImpl(EntityManager em) {
        this.em = em;
        queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<MembersDTO> members(MemberSearchForm memberSearchForm) {
        String searchKey = memberSearchForm.getSearchKey();
        String searchValue = memberSearchForm.getSearchValue();
        Grade searchGrade = memberSearchForm.getGrade();
        UserState searchState = memberSearchForm.getState();

        return queryFactory
                .select(Projections.fields(MembersDTO.class,
                        member.id.as("memberId"),
                        member.loginId,
                        member.username,
                        member.grade,
                        member.mobile,
                        member.state
                ))
                .from(member)
                .where(
                        byText(searchKey, searchValue),
                        byGrade(searchGrade),
                        byState(searchState)
                )
                .offset(memberSearchForm.getOffset())
                .limit(memberSearchForm.getLimit())
                .fetch();
    }

    private BooleanExpression byState(UserState searchState) {
        return searchState != null ? member.state.eq(searchState) : null;
    }
    private BooleanExpression byGrade(Grade searchGrade) {
        return searchGrade != null ? member.grade.eq(searchGrade) : null;
    }

    public BooleanExpression byText(String searchKey, String searchValue) {
        if(StringUtils.hasText(searchKey)) {
            switch (searchKey) {
                case "loginId":
                    return StringUtils.hasText(searchValue) ? member.loginId.like("%" + searchValue + "%") : null;

                case "username":
                    return StringUtils.hasText(searchValue) ? member.username.like("%" + searchValue + "%") : null;

                default: return null;
            }
        }
        return null;
    }
}
