package shop.soccerUniform.repository.cart;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import shop.soccerUniform.entity.Cart;
import shop.soccerUniform.entity.Member;
import shop.soccerUniform.entity.enumtype.Gender;
import shop.soccerUniform.entity.enumtype.Grade;
import shop.soccerUniform.entity.enumtype.Role;
import shop.soccerUniform.entity.enumtype.UserState;
import shop.soccerUniform.repository.member.MemberRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@Transactional
class CartRepositoryImplTest {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @BeforeEach
    void init() {
        Member member = new Member("1989.05.05", Gender.FEMALE, Grade.BRONZE, "2222", "3333");
        member.addDate(LocalDateTime.now(), LocalDateTime.now());
        member.addUser("member1", encoder.encode("1111"), "member1", "member1@naver.com", Role.ROLE_MEMBER, UserState.ABLE);
        memberRepository.save(member);
    }

    @Test
    void list() {
        Optional<Member> optionalMember = memberRepository.findByLoginId("member1");
        if(!optionalMember.isEmpty()) {
            Member member = optionalMember.get();
            List<Cart> cartList = cartRepository.findByMemberId(member.getId());
        }
    }
}