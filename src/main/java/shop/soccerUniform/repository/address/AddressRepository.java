package shop.soccerUniform.repository.address;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.soccerUniform.entity.Address;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long>, AddressQueryRepository {
    List<Address> findByMemberId(Long memberId);
}
