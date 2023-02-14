package shop.soccerUniform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.soccerUniform.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
