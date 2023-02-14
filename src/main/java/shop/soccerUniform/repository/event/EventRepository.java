package shop.soccerUniform.repository.event;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.soccerUniform.entity.Event;

public interface EventRepository extends JpaRepository<Event, Long>, EventQueryRepository {
}
