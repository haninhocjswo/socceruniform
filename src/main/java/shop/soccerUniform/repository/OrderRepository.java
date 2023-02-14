package shop.soccerUniform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.soccerUniform.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
