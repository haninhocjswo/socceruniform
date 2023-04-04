package shop.soccerUniform.repository.item;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.soccerUniform.entity.Item;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long>, ItemQueryRepository {
    Optional<Item> findByName(String name);
}
