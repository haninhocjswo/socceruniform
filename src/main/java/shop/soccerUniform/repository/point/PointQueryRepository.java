package shop.soccerUniform.repository.point;

import shop.soccerUniform.entity.Point;

import java.util.List;

public interface PointQueryRepository {
    List<Point> findByIds(List<Long> memberIds);
}
