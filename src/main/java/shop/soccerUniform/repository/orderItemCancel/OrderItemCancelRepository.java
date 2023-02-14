package shop.soccerUniform.repository.orderItemCancel;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.soccerUniform.entity.OrderItemCancel;

public interface OrderItemCancelRepository extends JpaRepository<OrderItemCancel, Long>, OrderItemCancelQueryRepository {
}
