package shop.soccerUniform.repository.point;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import shop.soccerUniform.entity.Point;

import javax.persistence.EntityManager;

@Repository
public class PointRepositoryImpl implements PointQueryRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    @Autowired
    public PointRepositoryImpl(EntityManager em) {
        this.em = em;
        queryFactory = new JPAQueryFactory(em);
    }

}
