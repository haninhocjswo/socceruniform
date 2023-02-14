package shop.soccerUniform.repository.itemOptionStock;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.soccerUniform.entity.ItemOptionStock;

public interface ItemOptionStockRepository extends JpaRepository<ItemOptionStock, Long>, ItemOptionStockQueryRepository {
}
