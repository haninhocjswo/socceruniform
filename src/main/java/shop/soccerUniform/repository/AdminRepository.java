package shop.soccerUniform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.soccerUniform.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {
}
