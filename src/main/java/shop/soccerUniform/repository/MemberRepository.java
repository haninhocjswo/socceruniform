package shop.soccerUniform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.soccerUniform.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
