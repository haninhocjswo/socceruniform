package shop.soccerUniform.repository.member;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.soccerUniform.entity.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberQueryRepository {
}
