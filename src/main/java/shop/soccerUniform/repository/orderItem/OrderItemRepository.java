package shop.soccerUniform.repository.orderItem;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.soccerUniform.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>, OrderItemQueryRepository {
}
