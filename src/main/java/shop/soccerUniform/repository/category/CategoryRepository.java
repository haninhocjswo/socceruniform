package shop.soccerUniform.repository.category;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.soccerUniform.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>, CategoryQueryRepository {
}
