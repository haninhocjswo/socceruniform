package shop.soccerUniform.repository.order;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.soccerUniform.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long>, OrderQueryRepository {
}
