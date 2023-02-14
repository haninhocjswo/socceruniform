package shop.soccerUniform.repository.account;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import shop.soccerUniform.entity.Account;

import javax.persistence.EntityManager;

@Repository
public class AccountRepositoryImpl implements AccountQueryRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    @Autowired

    public AccountRepositoryImpl(EntityManager em) {
        this.em = em;
        queryFactory = new JPAQueryFactory(em);
    }


}
