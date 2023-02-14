package shop.soccerUniform.repository.delivery;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.soccerUniform.entity.Delivery;

public interface DeliveryRepository extends JpaRepository<Delivery, Long>, DeliveryQueryRepository {
}
