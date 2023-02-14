package shop.soccerUniform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.soccerUniform.entity.OrderItemCancel;

public interface OrderItemCancelRepository extends JpaRepository<OrderItemCancel, Long> {
}
