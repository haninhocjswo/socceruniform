package shop.soccerUniform.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.util.StringUtils;
import shop.soccerUniform.entity.enumtype.Role;
import shop.soccerUniform.entity.enumtype.UserState;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;
import static javax.persistence.InheritanceType.JOINED;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Table(name = "T_USER")
@Getter
@ToString(of = {"id", "loginId", "username", "email", "role", "state"})
@Inheritance(strategy = JOINED)
@DiscriminatorColumn
@NoArgsConstructor(access = PROTECTED)
public abstract class User extends DateColumns {

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
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserState state;

    public void addUser(String loginId, String password, String username, String email, Role role, UserState state) {
        this.loginId = loginId;
        this.password = password;
        this.username = username;
        this.email = email;
        this.role = role;
        this.state = state;
    }

    public void editUser(String password, String username, String email, UserState state) {
        if(StringUtils.hasText(password)) {
            this.password = password;
        }
        this.username = username;
        this.email = email;
        if(state != null) {
            this.state = state;
        }
    }

    public void delUser(){
        state = UserState.DISABLE;
    }
}
