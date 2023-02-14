package shop.soccerUniform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.soccerUniform.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
