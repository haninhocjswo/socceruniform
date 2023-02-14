package shop.soccerUniform.repository.admin;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import shop.soccerUniform.entity.Admin;

import javax.persistence.EntityManager;

@Repository
public class AdminRepositoryImpl implements AdminQueryRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    @Autowired
    public AdminRepositoryImpl(EntityManager em) {
        this.em = em;
        queryFactory = new JPAQueryFactory(em);
    }

}
