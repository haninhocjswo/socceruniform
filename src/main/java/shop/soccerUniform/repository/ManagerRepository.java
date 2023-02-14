package shop.soccerUniform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.soccerUniform.entity.Manager;

public interface ManagerRepository extends JpaRepository<Manager, Long> {
}
