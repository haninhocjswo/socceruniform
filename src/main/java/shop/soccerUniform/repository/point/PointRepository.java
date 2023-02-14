package shop.soccerUniform.repository.point;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.soccerUniform.entity.Point;

public interface PointRepository extends JpaRepository<Point, Long>, PointQueryRepository {
}
