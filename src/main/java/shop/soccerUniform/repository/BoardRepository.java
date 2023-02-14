package shop.soccerUniform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.soccerUniform.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
