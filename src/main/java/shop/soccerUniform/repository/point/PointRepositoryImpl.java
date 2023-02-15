package shop.soccerUniform.repository.point;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import shop.soccerUniform.entity.Point;
import shop.soccerUniform.entity.QPoint;
import shop.soccerUniform.entity.enumtype.PointState;

import javax.persistence.EntityManager;
import java.util.List;

import static shop.soccerUniform.entity.QPoint.point1;

@Repository
public class PointRepositoryImpl implements PointQueryRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    @Autowired
    public PointRepositoryImpl(EntityManager em) {
        this.em = em;
        queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Point> findByIdsAndState(List<Long> memberIds, PointState pointState) {
        return queryFactory
                .selectFrom(point1)
                .where(
                        point1.state.eq(pointState),
                        point1.member.id.in(memberIds))
                .fetch();
    }

    @Override
    public Point findByIdAndState(Long memberId, PointState pointState) {
        return queryFactory
                .selectFrom(point1)
                .where(
                        point1.state.eq(pointState),
                        point1.member.id.eq(memberId))
                .fetchOne();
    }
}
