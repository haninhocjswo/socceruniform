package shop.soccerUniform.repository.member;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.apache.el.lang.ExpressionBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import shop.soccerUniform.entity.Member;
import shop.soccerUniform.entity.QMember;
import shop.soccerUniform.entity.dto.MemberSearchForm;
import shop.soccerUniform.entity.dto.MembersDTO;
import shop.soccerUniform.entity.enumtype.Grade;
import shop.soccerUniform.entity.enumtype.UserState;

import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Page<MembersDTO> members(MemberSearchForm memberSearchForm, Pageable pageable) {
        List<MembersDTO> members = memberList(memberSearchForm, pageable);
        Long count = countMemberList(memberSearchForm);

        return new PageImpl<>(members, pageable, count);
    }

    public List<MembersDTO> memberList(MemberSearchForm memberSearchForm, Pageable pageable) {
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
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    public Long countMemberList(MemberSearchForm memberSearchForm) {
        String searchKey = memberSearchForm.getSearchKey();
        String searchValue = memberSearchForm.getSearchValue();
        Grade searchGrade = memberSearchForm.getGrade();
        UserState searchState = memberSearchForm.getState();

        return queryFactory
                .select(member.count())
                .from(member)
                .where(
                        byText(searchKey, searchValue),
                        byGrade(searchGrade),
                        byState(searchState)
                )
                .fetchOne();
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
