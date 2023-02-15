package shop.soccerUniform.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import shop.soccerUniform.entity.Member;
import shop.soccerUniform.entity.Point;
import shop.soccerUniform.entity.dto.MemberForm;
import shop.soccerUniform.entity.enumtype.*;
import shop.soccerUniform.repository.member.MemberRepository;
import shop.soccerUniform.repository.point.PointRepository;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@Transactional
@Commit
class MemberServiceImplTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    PointRepository pointRepository;

    @Autowired
    EntityManager em;

    @BeforeEach
    void before() {
        Member member = new Member(Gender.FEMALE, Grade.BRONZE, "01-222-222", "123123213");
        member.addUser("aaaa", "1212", "aaaa", "aaaa@naver.com", Role.ROLE_MEMBER, UserState.ABLE);
        member.addDate(LocalDateTime.now(), LocalDateTime.now());
        em.persist(member);
        System.out.println("=============================================================");
        Point point = new Point(member, 0, 0, PointState.ABLE, 1);
        point.addDate(LocalDateTime.now(), LocalDateTime.now());
        em.persist(point);
        System.out.println("=============================================================");
        System.out.println("=============================================================");
    }

    @Test
    void save() {

    }

    @Test
    void findById_null() {
        Optional<Member> member = memberRepository.findById(-1L);

        assertThatThrownBy(() -> member.orElseThrow())
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void findById_notNull() {
        MemberForm member = memberService.findMember(1L);

        assertThat(member.getGrade()).isEqualTo(Grade.BRONZE);
        assertThat(member.getLoginId()).isEqualTo("aaaa");

        Point point = pointRepository.findByIdAndState(1L, PointState.ABLE);
        assertThat(member.getPoint()).isEqualTo(point.getPoint());
    }
}