package shop.soccerUniform.repository.address;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import shop.soccerUniform.entity.Address;

import javax.persistence.EntityManager;

@Repository
public class AddressRepositoryImpl implements AddressQueryRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    @Autowired

    public AddressRepositoryImpl(EntityManager em) {
        this.em = em;
        queryFactory = new JPAQueryFactory(em);
    }

}
