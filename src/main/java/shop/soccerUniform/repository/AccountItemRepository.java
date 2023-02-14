package shop.soccerUniform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.soccerUniform.entity.AccountItem;

public interface AccountItemRepository extends JpaRepository<AccountItem, Long> {
}
