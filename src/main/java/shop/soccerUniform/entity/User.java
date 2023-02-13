package shop.soccerUniform.entity;

import lombok.Getter;
import lombok.ToString;
import shop.soccerUniform.entity.enumtype.Role;
import shop.soccerUniform.entity.enumtype.UserState;

import javax.persistence.*;

import java.time.LocalDateTime;

import static javax.persistence.GenerationType.IDENTITY;
import static javax.persistence.InheritanceType.JOINED;

@Entity
@Table(name = "T_USER")
@Getter
@ToString(of = {"id", "loginId", "username", "email", "role", "state"})
@Inheritance(strategy = JOINED)
@DiscriminatorColumn
public class User extends DateColumns {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "USER_ID")
    private Long id;

    @Column(name = "LOGIN_ID", updatable = false, nullable = false)
    private String loginId;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private Role role;

    @Column(nullable = false)
    private UserState state;

}
