package shop.soccerUniform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.soccerUniform.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
