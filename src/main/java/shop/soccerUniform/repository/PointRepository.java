package shop.soccerUniform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.soccerUniform.entity.Point;

public interface PointRepository extends JpaRepository<Point, Long> {
}
