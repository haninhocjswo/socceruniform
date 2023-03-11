package shop.soccerUniform.repository.itemOptionValue;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.soccerUniform.entity.ItemOptionValue;

public interface ItemOptionValueRepository extends JpaRepository<ItemOptionValue, Long>, ItemOptionValueQueryRepository {
}
