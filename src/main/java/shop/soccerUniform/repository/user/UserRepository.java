package shop.soccerUniform.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.soccerUniform.entity.User;

public interface UserRepository extends JpaRepository<User, Long>, UserQueryRepository {
    public User findByLoginId(String loginId);
}
