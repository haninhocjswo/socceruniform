package shop.soccerUniform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.soccerUniform.entity.Delivery;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
}
