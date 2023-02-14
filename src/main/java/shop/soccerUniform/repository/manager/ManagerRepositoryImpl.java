package shop.soccerUniform.repository.manager;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import shop.soccerUniform.entity.Manager;

import javax.persistence.EntityManager;

@Repository
public class ManagerRepositoryImpl implements  ManagerQueryRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    @Autowired
    public ManagerRepositoryImpl(EntityManager em) {
        this.em = em;
        queryFactory = new JPAQueryFactory(em);
    }

}
