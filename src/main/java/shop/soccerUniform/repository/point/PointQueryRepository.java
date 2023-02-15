package shop.soccerUniform.repository.point;

import shop.soccerUniform.entity.Point;
import shop.soccerUniform.entity.enumtype.PointState;

import java.util.List;

public interface PointQueryRepository {
    List<Point> findByIdsAndState(List<Long> memberIds, PointState pointState);
    Point findByIdAndState(Long memberId, PointState pointState);
}
