package shop.soccerUniform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.soccerUniform.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
