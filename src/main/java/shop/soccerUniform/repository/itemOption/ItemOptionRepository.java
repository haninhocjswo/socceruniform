package shop.soccerUniform.repository.itemOption;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.soccerUniform.entity.ItemOption;

public interface ItemOptionRepository extends JpaRepository<ItemOption, Long>, ItemOptionQueryRepository {
}
