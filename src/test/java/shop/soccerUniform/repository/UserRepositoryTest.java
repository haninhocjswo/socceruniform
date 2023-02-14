package shop.soccerUniform.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import shop.soccerUniform.entity.Admin;
import shop.soccerUniform.entity.Manager;
import shop.soccerUniform.entity.Member;
import shop.soccerUniform.entity.User;
import shop.soccerUniform.entity.enumtype.*;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Commit
public class UserRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    ManagerRepository managerRepository;

    @Autowired
    EntityManager em;

    @BeforeEach
    void before() {
        Admin admin = new Admin("123123", "관리자");
        admin.putUser("admin", "123123", "관리자", "admin@naver.com", Role.ROLE_ADMIN, UserState.ABLE);
        admin.addDate(LocalDateTime.now(), LocalDateTime.now());

        Manager manager = new Manager("나이키", "123213-222", 12345, "서울", "중구",
                "나이키사장", "22232-3333", "담당자", "대리", "12323-222",
                "010-2222-3333", 30.0, DeliveryPolicy.ALL);
        manager.putUser("nike", "213123", "나이키", "nike@naver.com", Role.ROLE_MANAGER, UserState.ABLE);
        manager.addDate(LocalDateTime.now(), LocalDateTime.now());

        Member member = new Member(Gender.FEMALE, Grade.BRONZE, "010-2222-2222", "02-934-1222");
        member.putUser("member1", "213123", "회원1", "member1@naver.com", Role.ROLE_MEMBER, UserState.ABLE);
        member.addDate(LocalDateTime.now(), LocalDateTime.now());

        adminRepository.save(admin);
        managerRepository.save(manager);
        memberRepository.save(member);

        em.flush();
        em.clear();
    }

    @Test
    void save() {
        Member member = new Member(Gender.FEMALE, Grade.BRONZE, "222", "asdasd");

        member.putUser("test", "12345", "테스트", "test@gmail.com", Role.ROLE_MEMBER, UserState.ABLE);
        member.addDate(LocalDateTime.now(), LocalDateTime.now());

        memberRepository.save(member);

        em.flush();
        em.clear();

        List<Admin> admins = adminRepository.findAll();
        List<Manager> managers = managerRepository.findAll();
        List<Member> members = memberRepository.findAll();

        assertThat(admins.size()).isEqualTo(1);
        assertThat(managers.size()).isEqualTo(1);
        assertThat(members.size()).isEqualTo(2);

        members.stream().forEach(m -> System.out.println("username->" + m.getUsername()));
    }
}
