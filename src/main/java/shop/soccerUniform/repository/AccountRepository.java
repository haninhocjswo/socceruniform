package shop.soccerUniform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.soccerUniform.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
