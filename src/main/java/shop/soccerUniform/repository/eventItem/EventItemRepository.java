package shop.soccerUniform.repository.eventItem;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.soccerUniform.entity.EventItem;

public interface EventItemRepository extends JpaRepository<EventItem, Long>, EventItemQueryRepository {
}
