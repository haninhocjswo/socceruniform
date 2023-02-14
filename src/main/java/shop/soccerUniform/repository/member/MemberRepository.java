package shop.soccerUniform.repository.member;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.soccerUniform.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberQueryRepository {
}
